package dev.lupluv.ca8.events;

import dev.lupluv.ca8.Ca8;
import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.InventoryManager;
import dev.lupluv.ca8.utils.Strings;
import dev.lupluv.ca8.utils.Tab;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.List;

public class ClickHandler implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        InventoryView inv = p.getOpenInventory();
        ItemStack item = e.getCurrentItem();

        if(item != null){
            if(item.getType() != Material.AIR){

                if(inv.getTitle().equalsIgnoreCase("§7► §cSetup §7◄")){
                    e.setCancelled(true);
                    if(item.getType() == Material.SPAWNER){
                        p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                    }
                    if(item.getType() == Material.COMPASS){
                        p.openInventory(InventoryManager.getSetupLocationsInventory(p));
                    }
                    if(item.getType() == Material.GREEN_WOOL){
                        if(FileManager.getLocation("lobby") != null) {
                            if(FileManager.getLocation("spawn") != null) {
                                FileManager.setConfigValue("GameState", "LOBBY");
                                p.closeInventory();
                            }else
                                p.sendMessage(Strings.getPrefix() + " §cYou haven't set the Spawn Location!");
                        }else
                            p.sendMessage(Strings.getPrefix() + " §cYou haven't set the Lobby Location!");
                    }
                }
                if(inv.getTitle().equalsIgnoreCase("§7► §cOptions §7◄")){
                    e.setCancelled(true);
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nBookshelf")){
                        if(!FileManager.getConfigBool("Options.Drops.Bookshelf")){
                            FileManager.setConfigBool("Options.Drops.Bookshelf", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Drop Bookshelf to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Drops.Bookshelf", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Drop Bookshelf to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nSpawner")){
                        if(!FileManager.getConfigBool("Options.Drops.Spawner")){
                            FileManager.setConfigBool("Options.Drops.Spawner", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Drop Spawner to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Drops.Spawner", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Drop Spawner to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nEnd")){
                        if(!FileManager.getConfigBool("Options.Worlds.End")){
                            FileManager.setConfigBool("Options.Worlds.End", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the World End to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Worlds.End", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the World End to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nNether")){
                        if(!FileManager.getConfigBool("Options.Worlds.Nether")){
                            FileManager.setConfigBool("Options.Worlds.Nether", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the World Nether to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Worlds.Nether", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the World Nether to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nHeads")){
                        if(!FileManager.getConfigBool("Options.Features.SkullDropOnKill")){
                            FileManager.setConfigBool("Options.Features.SkullDropOnKill", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature SkullDropOnKill to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Features.SkullDropOnKill", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature SkullDropOnKill to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nLoginSystem")){
                        if(!FileManager.getConfigBool("Options.Features.UseLoginSystem")){
                            FileManager.setConfigBool("Options.Features.UseLoginSystem", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseLoginSystem to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Features.UseLoginSystem", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseLoginSystem to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nSleepSystem")){
                        if(!FileManager.getConfigBool("Options.Features.UseSleepSystem")){
                            FileManager.setConfigBool("Options.Features.UseSleepSystem", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseSleepSystem to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Features.UseSleepSystem", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseSleepSystem to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nDeathSystem")){
                        if(!FileManager.getConfigBool("Options.Features.UseChestOnDeathSystem")){
                            FileManager.setConfigBool("Options.Features.UseChestOnDeathSystem", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseChestOnDeathSystem to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Features.UseChestOnDeathSystem", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseChestOnDeathSystem to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nMotdSupport")){
                        if(!FileManager.getConfigBool("Options.Features.UseMotdSupport")){
                            FileManager.setConfigBool("Options.Features.UseMotdSupport", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseMotdSupport to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Features.UseMotdSupport", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseMotdSupport to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nTabSupport")){
                        if(!FileManager.getConfigBool("Options.Features.UseTabListSupport")){
                            FileManager.setConfigBool("Options.Features.UseTabListSupport", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseTabListSupport to the Value 'true'");
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.kickPlayer(Strings.getPrefix() + " §cPlease join again to make the change active!");
                            }
                        }else{
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.kickPlayer(Strings.getPrefix() + " §cPlease join again to make the change active!");
                            }
                            FileManager.setConfigBool("Options.Features.UseTabListSupport", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseTabListSupport to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6§nAutoBroadcast")){
                        if(!FileManager.getConfigBool("Options.Features.UseAutoBroadcast")){
                            FileManager.setConfigBool("Options.Features.UseAutoBroadcast", true);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseAutoBroadcast to the Value 'true'");
                        }else{
                            FileManager.setConfigBool("Options.Features.UseAutoBroadcast", false);
                            p.sendMessage(Strings.getPrefix() + " §cYou have set the Feature UseAutoBroadcast to the Value 'false'");
                        }
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupOptionsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getType() == Material.RED_STAINED_GLASS_PANE){
                        p.openInventory(InventoryManager.getSetupInventory(p));
                    }
                }

                if(inv.getTitle().equalsIgnoreCase("§7► §cLocations §7◄")){
                    e.setCancelled(true);
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§7» §cLobby")){
                        FileManager.setLocation("lobby", p.getLocation());
                        p.sendMessage(Strings.getPrefix() + "§cYou have set the Location 'lobby'");
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupLocationsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§7» §cSpawn")){
                        FileManager.setLocation("spawn", p.getLocation());
                        p.sendMessage(Strings.getPrefix() + "§cYou have set the Location 'spawn'");
                        p.closeInventory();
                        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                p.openInventory(InventoryManager.getSetupLocationsInventory(p));
                            }
                        }, 15);
                    }
                    if(item.getType() == Material.RED_STAINED_GLASS_PANE){
                        p.openInventory(InventoryManager.getSetupInventory(p));
                    }
                }

                if(inv.getTitle().equalsIgnoreCase("§7» §6Status")){
                    e.setCancelled(true);
                    if(item.getType() == Material.PLAYER_HEAD){
                        if(!item.getItemMeta().getDisplayName().startsWith("§cDein Status")){
                            String name = item.getItemMeta().getDisplayName();
                            for(int i = 0; i <= 9; i++){
                                if(name.equalsIgnoreCase(FileManager.getStatus().getString("Status." + i + ".DisplayName"))){
                                    FileManager.setStatus(FileManager.getStatus().getString("Status." + i + ".Name"), p);
                                    p.closeInventory();
                                }
                            }
                        }
                    }
                    Tab.update(p);
                }

                if(inv.getTitle().equalsIgnoreCase("§7» §6All Stats")){
                    e.setCancelled(true);

                    List<String> list = FileManager.getRegisteredPlayers();

                    for (String uuid : list) {

                        if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§6" + FileManager.getNameByUUID(uuid) + "'s Stats")){
                            p.openInventory(InventoryManager.getProfileStatsInventory(uuid));
                        }

                    }

                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§cClose")){
                        p.closeInventory();
                    }
                }

                List<String> list = FileManager.getRegisteredPlayers();
                for (String uuid : list) {
                    String name = FileManager.getNameByUUID(uuid);
                    if(inv.getTitle().equalsIgnoreCase("§7» §e" + name + "'s Stats")){
                        e.setCancelled(true);
                        if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§c« Back")){
                            p.openInventory(InventoryManager.getStatsMainInventory(1));
                        }
                    }
                }

                if(p.getInventory().getChestplate() != null) {
                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase(p.getInventory().getChestplate().getItemMeta().getDisplayName())) {
                        if (item.getItemMeta().getDisplayName().equalsIgnoreCase(FileManager.getMessage("ElytraName"))) {
                            e.setCancelled(true);
                        }
                    }
                }

            }
        }

    }

}
