package dev.lupluv.ca8.events;

import dev.lupluv.ca8.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertyHandler implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){

        if(!FileManager.getConfigValue("GameState").equalsIgnoreCase("ingame")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if(!FileManager.getConfigValue("GameState").equalsIgnoreCase("ingame")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Location bLoc = e.getBlock().getLocation();
        Location spawn = FileManager.getLocation("spawn");
        if(!Util.loginedPlayers.contains(e.getPlayer()))
            e.setCancelled(true);
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("lobby")){
            e.setCancelled(true);
        }
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("ingame")){
            if(bLoc.distance(spawn) < FileManager.getConfigInt("SpawnProtectRadius")){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        Location bLoc = e.getBlock().getLocation();
        Location spawn = FileManager.getLocation("spawn");
        if(!Util.loginedPlayers.contains(e.getPlayer()))
            e.setCancelled(true);
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("lobby")){
            e.setCancelled(true);
        }
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("ingame")){
            if(bLoc.distance(spawn) < FileManager.getConfigInt("Ingame.SpawnProtectRadius")){
                e.setCancelled(true);
            }

            // Drops

            Block block = e.getBlock();

            if (block.getType() == Material.SPAWNER) {
                if (FileManager.getConfigBool("Options.Drops.Spawner")) {
                    p.getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.SPAWNER, 1));
                }
            }
            if(block.getType() == Material.BOOKSHELF){
                if(FileManager.getConfigBool("Options.Drops.Bookshelf")){
                    p.getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.BOOKSHELF, 1));
                }
            }




        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e){
        if(e.getReason().equalsIgnoreCase("Flying is not enabled on this server")){
            if(FileManager.getConfigValue("GameState").equalsIgnoreCase("lobby")){
                e.setCancelled(true);
            }
        }
    }

    public static Map<String, Location> deathLogs = new HashMap<String, Location>();

    @EventHandler
    public void onDeath(EntityDeathEvent e) throws IOException {

        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            PlayerProfile profile = FileManager.getPlayerProfile(p.getName());
            profile.addDeath();
            FileManager.setPlayerProfile(profile);
            if(e.getEntity().getKiller() instanceof Player){
                Player k = e.getEntity().getKiller();
                PlayerProfile profileKiller = FileManager.getPlayerProfile(k.getName());
                profileKiller.addKill();
                FileManager.setPlayerProfile(profileKiller);
            }
        }

        if(FileManager.getConfigBool("Options.Features.SkullDropOnKill")){
            if(e.getEntity().getKiller() instanceof Player){
                if(e.getEntity() instanceof Player) {
                    Player killer = (Player) e.getEntity().getKiller();
                    Item item = new Item(Material.PLAYER_HEAD);
                    item.setDisplayName("§e" + e.getEntity().getName());
                    item.setSkullOwner(e.getEntity().getName());
                    e.getDrops().add(item.build());
                }
            }
        }

        if(FileManager.getConfigBool("Options.Features.UseChestOnDeathSystem")){
            if(e.getEntity() instanceof  Player){
                Player d = (Player) e.getEntity();
                Location loc = d.getLocation();
                Block block = loc.getBlock();
                block.setType(Material.CHEST);
                BlockState bs = block.getState();
                Chest c = (Chest) bs;
                block.setType(Material.CHEST);
                d.getInventory().forEach(item ->{
                    if(item != null) {
                        c.getInventory().addItem(item);
                    }
                });
                e.getDrops().clear();
                deathLogs.put(d.getName(), loc);
            }
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

    }

    @EventHandler
    public void onWorldChanged(PlayerPortalEvent e){
        if(e.getTo().getWorld().getEnvironment() == World.Environment.NETHER){
            if(!FileManager.getConfigBool("Options.Worlds.Nether")){
                e.setCancelled(true);
            }
        }
        if(e.getTo().getWorld().getEnvironment() == World.Environment.THE_END){
            if(!FileManager.getConfigBool("Options.Worlds.End")){
                e.setCancelled(true);
            }
        }
    }

    public static int playersInBed;

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e){
        int needed = FileManager.getConfigInt("Ingame.HowMuchPlayersNeedToBeInBedToMakeDay");
        Player p = e.getPlayer();
        if(p.getWorld().getTime() >= 12528) {
            if (FileManager.getConfigBool("Options.Features.UseSleepSystem")) {
                if (playersInBed < needed) {
                    playersInBed++;
                    if (playersInBed >= needed) {
                        FileManager.getLocation("spawn").getWorld().setTime(0);
                        Bukkit.broadcastMessage(FileManager.getMessage("SleepSucess"));
                        playersInBed = 0;
                    } else {
                        Bukkit.broadcastMessage(FileManager.getMessage("PlayersInBed").replace("%amount%"
                                , String.valueOf(playersInBed)).replace("%missing%", String.valueOf(needed - playersInBed)));
                    }
                }
            }
        }else {
            e.setCancelled(true);
            p.sendMessage(Strings.getPrefix() + "§cYou can only Sleep at night!");
        }
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e){
        Player p = e.getPlayer();
        if (FileManager.getConfigBool("Options.Features.UseSleepSystem")) {
            if(p.getWorld().getTime() >= 12528) {
                playersInBed--;
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(FileManager.getRegisteredPlayers().contains(p.getUniqueId().toString())) {
            if (p.getBedSpawnLocation() == null) {
                e.setRespawnLocation(FileManager.getLocation("spawn"));
                p.sendMessage(FileManager.getMessage("PlayerHasNoBed"));
            }
            if (FileManager.getConfigBool("Options.Features.UseElytraSystem")) {
                GameManage.giveStarterKit(p);
            }
            if (FileManager.getConfigBool("Options.Features.UseChestOnDeathSystem")) {

                if (deathLogs.containsKey(p.getName())) {
                    Location loc = deathLogs.get(p.getName());
                    ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bm = (BookMeta) is.getItemMeta();
                    bm.addPage("X: " + loc.getBlockX() + "\nY: " + loc.getBlockY() + "\nZ: " + loc.getBlockZ() + "\nWelt: " + loc.getWorld().getName());
                    bm.setAuthor("§6Notch");
                    bm.setTitle("§6Your Death Location");
                    bm.setDisplayName("§6Your Death Location");
                    is.setItemMeta(bm);
                    deathLogs.remove(p.getName());
                    p.getInventory().addItem(is);
                }

            }
            p.setGameMode(GameMode.SURVIVAL);
        }else{
            p.teleport(FileManager.getLocation("spawn"));
            p.getInventory().clear();
            p.setGameMode(GameMode.SPECTATOR);
            p.setLevel(0);
            p.setExp(0);
            p.getActivePotionEffects().clear();
            p.setAllowFlight(true);
            p.sendMessage(Strings.getPrefix() + "§cDu bist kein Teilnehmender Spieler und wirst daher in den Spectator Modus gesetzt!");
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent e){
        if(FileManager.getConfigBool("Options.Features.UseMotdSupport")) {
            String motd = FileManager.getMotd(1) + "\n" + FileManager.getMotd(2);
            e.setMotd(motd.replace("%online_players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replace("%max_players%", String.valueOf(Bukkit.getMaxPlayers())).replace("%prefix%", Strings.getPrefix())
                    .replace("%>>%", "»").replace("%<<%", "«").replace("%status%", FileManager.getConfigValue("GameState"))
                    .replace("&", "§"));
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();

        if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(FileManager.getMessage("ElytraName"))){
            e.setCancelled(true);
        }
        if(e.getItemDrop().getItemStack().getType() == Material.FIREWORK_ROCKET){
            if(p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase(FileManager.getMessage("ElytraName"))){
                e.setCancelled(true);
            }
        }

    }



}





















