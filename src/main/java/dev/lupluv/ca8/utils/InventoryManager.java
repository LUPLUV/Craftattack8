package dev.lupluv.ca8.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    public static Inventory getSetupInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 9*3, "§7► §cSetup §7◄");

        Item locs = new Item(Material.COMPASS);
        locs.setDisplayName("§7» §cLocations");
        locs.setHideAttributes(true);
        locs.setHideEnchants(true);
        locs.setLore(Lore.create(" ", "§3Here you can set Locations,", "§3the Spawn and the Lobby.", " "));
        inv.setItem(10, locs.build());

        Item options = new Item(Material.SPAWNER);
        options.setDisplayName("§7» §cOptions");
        options.setHideAttributes(true);
        options.setHideEnchants(true);
        options.setLore(Lore.create(" ", "§3Here you can edit all Settings,", "§3like Drops and activated Worlds.", " "));
        inv.setItem(12, options.build());

        Item start = new Item(Material.GREEN_WOOL);
        start.setDisplayName("§7» §cSave to Lobby Mode");
        start.setHideAttributes(true);
        start.setHideEnchants(true);
        start.setLore(Lore.create(" ", "§3Here you can end the Setup.", " "));
        inv.setItem(16, start.build());

        return inv;
    }

    public static Inventory getSetupOptionsInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 9*5, "§7► §cOptions §7◄");


        /* Drops */
        Item options = new Item(Material.BROWN_DYE);
        options.setDisplayName("§7» §9Drops §7§l»");
        options.setHideAttributes(true);
        options.setHideEnchants(true);
        options.setLore(Lore.create(" ", "§3All Drops can be enabled", "§3or disabled.", " "));
        inv.setItem(0, options.build());

        Item bookshelf = new Item(Material.BOOKSHELF);
        bookshelf.setDisplayName("§6§nBookshelf");
        bookshelf.setHideAttributes(true);
        bookshelf.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Drops.Bookshelf")) {
            bookshelf.setLore(Lore.create("§3- §7Should a bookshelf drop", "§7when you break it?", "§9Drop §8» §a§lENABLED"));
            bookshelf.setShiny(true);
        }else{
            bookshelf.setLore(Lore.create("§3- §7Should a bookshelf drop", "§7when you break it?", "§9Drop §8» §c§lDISABLED"));
        }
        inv.setItem(2, bookshelf.build());

        Item spawner = new Item(Material.SPAWNER);
        spawner.setDisplayName("§6§nSpawner");
        spawner.setHideAttributes(true);
        spawner.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Drops.Spawner")) {
            spawner.setLore(Lore.create("§3- §7Should a spawner drop", "§7when you break it?", "§9Drop §8» §a§lENABLED"));
            spawner.setShiny(true);
        }else{
            spawner.setLore(Lore.create("§3- §7Should a spawner drop", "§7when you break it?", "§9Drop §8» §c§lDISABLED"));
        }
        inv.setItem(3, spawner.build());


        /* Worlds */
        Item worlds = new Item(Material.CRYING_OBSIDIAN);
        worlds.setDisplayName("§7» §9Worlds §7§l»");
        worlds.setHideAttributes(true);
        worlds.setHideEnchants(true);
        worlds.setLore(Lore.create(" ", "§3All Worlds can be enabled", "§3or disabled.", "§3You can change this when the", "§3Game is startet too.", " "));
        inv.setItem(9, worlds.build());

        Item end = new Item(Material.END_PORTAL_FRAME);
        end.setDisplayName("§6§nEnd");
        end.setHideAttributes(true);
        end.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Worlds.End")) {
            end.setLore(Lore.create("§3- §7Should somebody can", "§7enter the World?", "§9World §8» §a§lENABLED"));
            end.setShiny(true);
        }else{
            end.setLore(Lore.create("§3- §7Should somebody can", "§7enter the World?", "§ßWorld §8» §c§lDISABLED"));
        }
        inv.setItem(11, end.build());

        Item nether = new Item(Material.OBSIDIAN);
        nether.setDisplayName("§6§nNether");
        nether.setHideAttributes(true);
        nether.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Worlds.Nether")) {
            nether.setLore(Lore.create("§3- §7Should somebody can", "§7enter the World?", "§9World §8» §a§lENABLED"));
            nether.setShiny(true);
        }else{
            nether.setLore(Lore.create("§3- §7Should somebody can", "§7enter the World?", "§ßWorld §8» §c§lDISABLED"));
        }
        inv.setItem(12, nether.build());

        Item endcity = new Item(Material.ELYTRA);
        endcity.setDisplayName("§6§nEndcity (coming soon)");
        endcity.setHideAttributes(true);
        endcity.setHideEnchants(true);
        endcity.setLore(Lore.create("§3- §7Should somebody can", "§7enter the World?", "§9World §8» §a§lENABLED"));
        endcity.setShiny(true);
        inv.setItem(13, endcity.build());

        /* Features */
        Item features = new Item(Material.END_CRYSTAL);
        features.setDisplayName("§7» §9Features §7§l»");
        features.setHideAttributes(true);
        features.setHideEnchants(true);
        features.setLore(Lore.create(" ", "§3All Features can be enabled", "§3or disabled.", "§3You can change this when the", "§3Game is startet too.", " "));
        inv.setItem(18, features.build());

        Item skulls = new Item(Material.PLAYER_HEAD);
        skulls.setDisplayName("§6§nHeads");
        skulls.setHideAttributes(true);
        skulls.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.SkullDropOnKill")) {
            skulls.setLore(Lore.create("§3- §7Should somebody get", "§7the Head of his kill?", "§9Head §8» §a§lENABLED"));
            skulls.setShiny(true);
        }else{
            skulls.setLore(Lore.create("§3- §7Should somebody get", "§7the Head of his kill?", "§9Head §8» §c§lDISABLED"));
        }
        inv.setItem(20, skulls.build());

        Item loginSystem = new Item(Material.BREWING_STAND);
        loginSystem.setDisplayName("§6§nLoginSystem");
        loginSystem.setHideAttributes(true);
        loginSystem.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.UseLoginSystem")){
            loginSystem.setLore(Lore.create("§3- §7Do you want to", "§7use a Login System?", "§9LoginSystem §8» §a§lENABLED"));
            loginSystem.setShiny(true);
        }else{
            loginSystem.setLore(Lore.create("§3- §7Do you want to", "§7use a Login System?", "§9LoginSystem §8» §c§lDISABLED"));
        }
        inv.setItem(21, loginSystem.build());

        Item sleepSystem = new Item(Material.RED_BED);
        sleepSystem.setDisplayName("§6§nSleepSystem");
        sleepSystem.setHideAttributes(true);
        sleepSystem.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.UseSleepSystem")){
            sleepSystem.setLore(Lore.create("§3- §7Do you want to", "§7use a Sleep System?", "§7You can set Values in config.yml", "§9SleepSystem §8» §a§lENABLED"));
            sleepSystem.setShiny(true);
        }else{
            sleepSystem.setLore(Lore.create("§3- §7Do you want to", "§7use a Sleep System?", "§7You can set Values in config.yml", "§9SleepSystem §8» §c§lDISABLED"));
        }
        inv.setItem(22, sleepSystem.build());

        Item deathSystem = new Item(Material.CHEST);
        deathSystem.setDisplayName("§6§nDeathSystem");
        deathSystem.setHideAttributes(true);
        deathSystem.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.UseChestOnDeathSystem")){
            deathSystem.setLore(Lore.create("§3- §7Do you want to", "§7use our Death System?", "§9DeathSystem §8» §a§lENABLED"));
            deathSystem.setShiny(true);
        }else{
            deathSystem.setLore(Lore.create("§3- §7Do you want to", "§7use our Death System?", "§9DeathSystem §8» §c§lDISABLED"));
        }
        inv.setItem(23, deathSystem.build());

        Item motdSupport = new Item(Material.PAPER);
        motdSupport.setDisplayName("§6§nMotdSupport");
        motdSupport.setHideAttributes(true);
        motdSupport.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.UseMotdSupport")){
            motdSupport.setLore(Lore.create("§3- §7Do you want to", "§7use the Motd Support?", "§9MotdSupport §8» §a§lENABLED"));
            motdSupport.setShiny(true);
        }else{
            motdSupport.setLore(Lore.create("§3- §7Do you want to", "§7use the Motd Support?", "§9MotdSupport §8» §c§lDISABLED"));
        }
        inv.setItem(24, motdSupport.build());

        Item tabSupport = new Item(Material.GLASS_BOTTLE);
        tabSupport.setDisplayName("§6§nTabSupport");
        tabSupport.setHideAttributes(true);
        tabSupport.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.UseTabListSupport")){
            tabSupport.setLore(Lore.create("§3- §7Do you want to", "§7use the Tab Support?", "§9TabSupport §8» §a§lENABLED"));
            tabSupport.setShiny(true);
        }else{
            tabSupport.setLore(Lore.create("§3- §7Do you want to", "§7use the Tab Support?", "§9TabSupport §8» §c§lDISABLED"));
        }
        inv.setItem(25, tabSupport.build());

        Item autoBc = new Item(Material.REDSTONE_BLOCK);
        autoBc.setDisplayName("§6§nAutoBroadcast");
        autoBc.setHideAttributes(true);
        autoBc.setHideEnchants(true);
        if(FileManager.getConfigBool("Options.Features.UseAutoBroadcast")){
            autoBc.setLore(Lore.create("§3- §7Do you want to", "§7use the Auto Broadcast?", "§9AutoBroadcast §8» §a§lENABLED"));
            autoBc.setShiny(true);
        }else{
            autoBc.setLore(Lore.create("§3- §7Do you want to", "§7use the Auto Broadcast?", "§9AutoBroadcast §8» §c§lDISABLED"));
        }
        inv.setItem(26, autoBc.build());

        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("setup")) {
            Item back = new Item(Material.RED_STAINED_GLASS_PANE);
            back.setDisplayName("§7« §cBack");
            back.setHideAttributes(true);
            inv.setItem(9 * 5 - 1, back.build());
        }

        return inv;
    }

    public static Inventory getSetupLocationsInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 9*3, "§7► §cLocations §7◄");

        Item spawn = new Item(Material.CLOCK);
        spawn.setDisplayName("§7» §cSpawn");
        spawn.setHideAttributes(true);
        spawn.setHideEnchants(true);
        spawn.setLore(Lore.create(" ", "§3Click to set the Spawn", "§3at your position.", " "));
        inv.setItem(11, spawn.build());

        Item lobby = new Item(Material.CLOCK);
        lobby.setDisplayName("§7» §cLobby");
        lobby.setHideAttributes(true);
        lobby.setHideEnchants(true);
        lobby.setLore(Lore.create(" ", "§3Click to set the Lobby", "§3at your position.", " "));
        inv.setItem(13, lobby.build());

        Item spec = new Item(Material.CLOCK);
        spec.setDisplayName("§7» §cSpectator (coming soon)");
        spec.setHideAttributes(true);
        spec.setHideEnchants(true);
        spec.setLore(Lore.create(" ", "§3Click to set the Spectator", "§3at your position.", " "));
        inv.setItem(15, spec.build());

        Item back = new Item(Material.RED_STAINED_GLASS_PANE);
        back.setDisplayName("§7« §cBack");
        back.setHideAttributes(true);
        inv.setItem(9*3-1, back.build());

        return inv;
    }

    public static Inventory getStatusInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*6, "§7» §6Status");

        Item pHead = new Item(Material.PLAYER_HEAD);
        pHead.setDisplayName("§cDein Status §7» §8[" + FileManager.getStatus(p) + "§8]");
        pHead.setHideAttributes(true);
        pHead.setSkullOwner(p.getName());
        inv.setItem(13, pHead.build());

        for(int i = 1; i <= 9; i++){
            FileConfiguration cfg = FileManager.getStatus();
            if(cfg.getBoolean("Status." + i + ".Show")){
                inv.setItem(cfg.getInt("Status." + i + ".Slot")
                        , Util.createCustomSkull(cfg.getString("Status." + i + ".Texture")
                                , cfg.getString("Status." + i + ".DisplayName"), cfg.getStringList("Status." + i + ".Lore")));
            }
        }

        return inv;
    }

    public static Inventory getStatsMainInventory(int page){
        Inventory inv = Bukkit.createInventory(null, 9*6, "§7» §6All Stats");

        int amount = FileManager.getRegisteredPlayers().size();
        int pages;

        if(page == 1) {
            if (amount <= 45) {
                int slot = 0;
                for (String uuid : FileManager.getRegisteredPlayers()) {

                    String name = FileManager.getNameByUUID(uuid);

                    Item head = new Item(Material.PLAYER_HEAD);
                    head.setDisplayName("§6" + name + "'s Stats");
                    head.setHideEnchants(true);
                    head.setHideAttributes(true);
                    head.setLore(Lore.create("§3 Klicke um mehr zu erfahren!"));
                    head.setSkullOwner(name);
                    inv.setItem(slot, head.build());
                    slot++;

                }

                Item close = new Item(Material.RED_STAINED_GLASS_PANE);
                close.setDisplayName("§cClose");
                close.setHideAttributes(true);
                inv.setItem(9*6-5, close.build());

            }else{
                ItemStack next = Util.createCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRiZThhZWVjMTE4NDk2OTdh" +
                        "ZGM2ZmQxZjE4OWIxNjY0MmRmZjE5ZjI5NTVjMDVkZWFiYTY4YzlkZmYxYmUifX19", "§bNext Page »", null);
                inv.setItem(9*6-4, next);
            }
        }

        return inv;

    }

    public static Inventory getProfileStatsInventory(String uuid){
        String name = FileManager.getNameByUUID(uuid);
        PlayerProfile profile = FileManager.getPlayerProfile(name);
        Inventory inv = Bukkit.createInventory(null, 9*6, "§7» §e" + name + "'s Stats");

        Item head = new Item(Material.PLAYER_HEAD);
        head.setDisplayName("§cName §7» " + name);
        head.setHideEnchants(true);
        head.setHideEnchants(true);
        head.setLore(Lore.create(" ", "§cKills: §3" + profile.getKills(), "§cDeaths: §3" + profile.getDeaths(), "§cStatus: §3" + profile.getStatus(), " "));
        head.setSkullOwner(name);
        inv.setItem(13, head.build());

        Item kills = new Item(Material.DIAMOND_SWORD);
        kills.setDisplayName("§cKills §7» §3§l" + profile.getKills());
        kills.setHideAttributes(true);
        kills.setHideEnchants(true);
        inv.setItem(19, kills.build());

        Item status = new Item(Material.PAPER);
        status.setDisplayName("§cStatus §7» §3§l" + profile.getStatus());
        status.setHideEnchants(true);
        status.setHideAttributes(true);
        inv.setItem(30, status.build());

        Item deaths = new Item(Material.TOTEM_OF_UNDYING);
        deaths.setDisplayName("§cDeaths §7» §3§l" + profile.getDeaths());
        deaths.setHideAttributes(true);
        deaths.setHideEnchants(true);
        inv.setItem(32, deaths.build());

        Item clan = new Item(Material.IRON_CHESTPLATE);
        clan.setDisplayName("§cClan §7» §3§lcoming soon");
        clan.setHideEnchants(true);
        clan.setHideAttributes(true);
        inv.setItem(25, clan.build());

        Item back = new Item(Material.RED_STAINED_GLASS_PANE);
        back.setDisplayName("§c« Back");
        back.setHideAttributes(true);
        back.setHideEnchants(true);
        inv.setItem(9*6-1, back.build());

        return inv;
    }

}
