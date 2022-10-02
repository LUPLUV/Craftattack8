package dev.lupluv.ca8.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static File getFile(String name){
        File file = new File("plugins//Craftattack8//" + name);
        return file;
    }

    public static FileConfiguration getConfiguration(File file){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg;
    }

    public static void loadFiles() throws IOException {

        File ordner = new File("plugins//Craftattack8");
        File configFile = new File("plugins//Craftattack8//config.yml");
        File messagesFile = new File("plugins//Craftattack8//messages.yml");
        File permissionsFile = new File("plugins//Craftattack8//permissions.yml");
        File profilesOrdner = new File("plugins//Craftattack8//Profiles");
        File teamsFile = new File("plugins//Craftattack8//teams.yml");
        File locationsFile = new File("plugins//Craftattack8//locations.yml");
        File dataOrdner = new File("plugins//Craftattack8//Data");
        File dataFile = new File("plugins//Craftattack8//Data//data.yml");
        File passwordsFile = new File("plugins//Craftattack8//Data//passwords.yml");
        File rulesFile = new File("plugins//Craftattack8//rules.yml");
        File motdFile = new File("plugins//Craftattack8//motd.yml");
        File statusFile = new File("plugins//Craftattack8//status.yml");
        File chatFile = new File("plugins//Craftattack8//chat.yml");

        if(!ordner.exists())
            ordner.mkdir();
        if(!profilesOrdner.exists())
            profilesOrdner.mkdir();
        if(!configFile.exists()){
            configFile.createNewFile();
            setConfigValue("Version (Dont change)", "1.0");
            setConfigValue("Prefix", "&7[&2CraftAttack&7]&r");
            setConfigValue("GameState", "SETUP");
            setConfigBool("AlreadyStarted", false);
            setConfigBool("Options.Drops.Bookshelf", false);
            setConfigBool("Options.Drops.Spawner", false);
            setConfigBool("Options.Worlds.End", true);
            setConfigBool("Options.Worlds.Nether", true);
            setConfigBool("Options.Worlds.Endcity", true);
            setConfigBool("Options.Features.SkullDropOnKill", true);
            setConfigBool("Options.Features.UseLoginSystem", true);
            setConfigBool("Options.Features.UseSleepSystem", true);
            setConfigBool("Options.Features.UseChestOnDeathSystem", false);
            setConfigBool("Options.Features.UseMotdSupport", true);
            setConfigBool("Options.Features.UseTabListSupport", true);
            setConfigBool("Options.Features.UseAutoBroadcast", true);
            setConfigBool("Options.Features.UseElytraSystem", true);
            setConfigBool("Lobby.AllowLobbyFly", true);
            setConfigBool("Lobby.HasToBeOnlineAtStart", true);
            setConfigInt("Ingame.HowMuchPlayersNeedToBeInBedToMakeDay", 3);
            setConfigInt("Ingame.SpawnProtectRadius", 12);
            setConfigInt("AutoBroadCast.SecondsBetweenEveryMessage", 60);
            List<String> l = new ArrayList<>();
            l.add("&6Join my Discord: https://discord.gg/3ANZTqR7ZG");
            setConfigList("AutoBroadCast.Messages", l);
        }
        if(!messagesFile.exists()){
            messagesFile.createNewFile();
            setMessage("NoPerms", "%prefix% &cDazu hast du keine Rechte!");
            setMessage("PlayerNotFound", "%prefix% &cDer Spieler %player% wurde nicht gefunden!");
            setMessage("NoConsoleCommand", "%prefix% &cDiesen Command kannst du nur als Spieler ausführen!");
            setMessage("CommandUsage", "%prefix% &3Bitte benutze %command%");
            setMessage("TabListHeader", " \n Dies ist der Header \n ");
            setMessage("TabListFooter", " \n Dies ist der Footer \n ");
            setMessage("JoinMessage", "%prefix% &8[&a+&8] &7%player%");
            setMessage("QuitMessage", "%prefix% &8[&c-&8] &7%player%");
            setMessage("LoginInfo", "%prefix% &cBitte melde dich an mit /login <passwort>");
            setMessage("RegisterInfo", "%prefix% &cBitte registriere dich mit /register <passwort> <passwort>");
            setMessage("PasswordRight", "%prefix% &aDu hast dich erfolgreich eingeloggt!");
            setMessage("PasswordWrong", "%prefix% &cDas eingegebene Passwort ist Falsch!");
            setMessage("PasswordWrongCase", "%prefix% &cDie Groß/Klein schreibung ist Falsch!");
            setMessage("RegisterPasswordsNotSame", "%prefix% &cDie Passwörter stimmen nicht überein!");
            setMessage("RegisterRight", "%prefix% &aDu hast dich erfolgreich registriert!");
            setMessage("NotRegistered", "%prefix% &cDu bist noch nicht registriert, benutze /register <passwort> <passwort>");
            setMessage("AlreadyRegistered", "%prefix% &cDu bist bereits Registriert, wenn du dich neu registrieren möchtest schreibe /pwreset " +
                    "<altes passwort> und du kannst beim nächsten joinen wieder /register <passwort> <passwort> eingeben!");
            setMessage("AlreadyLoggedIn", "%prefix% &cDu bist bereits eingeloggt!");
            setMessage("LoginTimeoutKick", "%prefix% &cDu wurdest gekickt da du nicht \n &cals 30 Sekunden zum" +
                    " anmelden brauchen darfst! \n   \n &6Falls du Hilfe brauchst melde dich auf unserem TeamSpeak. \n &bTeamSpeak &8%>>% &7ts.meinserver.com");
            setMessage("PwresetKick", "%prefix% &cDu hast dein Passwort gelöscht, somit kannst du dich nun mit einem neuen Registrieren! \n &6Falls du Hilfe brauchst melde dich auf unserem TeamSpeak! \n &bTeamSpeak: &8» &7ts.meinserver.com");
            setMessage("ElytraName", "&7%>>% &eElytra");
            setMessage("ElytraLore", "&3Mit dieser Elytra kannst du nach unten fliegen!");
            setMessage("CountdownChat", "%prefix% &aDas Projekt startet in %seconds% Sekunden");
            setMessage("CountdownTitleLine1", "&eDas Projekt startet,");
            setMessage("CountdownTitleLine2", "&7in %seconds% Sekunden");
            setMessage("PlayersInBed", "%prefix% &cEs sind %amount% Spieler in Betten! Noch %missing% dass es Tag wird!");
            setMessage("SleepSucess", "%prefix% &aDie Uhrzeit wurde auf Tag gestellt!");
            setMessage("PlayerHasNoBed", "%prefix% &cDa du deinen Spawn mit keinem Bett festgelegt hast, respawnst du am normalen Spawn.");
            setMessage("DisconnectOnDeath", "%prefix% &cAnscheinend bist du bei deinem Tod disconnected und nicht respawnt, daher bekommst du nicht die Koordinaten der Chest mit deinen Items :(");
        }
        if(!permissionsFile.exists()){
            permissionsFile.createNewFile();
            setPermissionValue("setup", "craftattack.command.setup");
            setPermissionValue("craftattack", "craftattack.command.craftattack");
            setPermissionValue("register", "craftattack.command.register");
            setPermissionValue("login", "craftattack.command.login");
            setPermissionValue("status", "craftattack.command.status");
            setPermissionValue("pwreset", "craftattack.command.pwreset");
            setPermissionValue("options", "craftattack.command.options");
            setPermissionValue("stats", "craftattack.command.stats");
        }
        if(!teamsFile.exists()){
            teamsFile.createNewFile();
        }
        if(!locationsFile.exists()){
            locationsFile.createNewFile();
        }
        if(!dataOrdner.exists())
            dataOrdner.mkdir();
        if(!dataFile.exists()){
            dataFile.createNewFile();
            setRegisteredPlayers(new ArrayList<>());
            setIngamePlayers(new ArrayList<>());
        }
        if(!passwordsFile.exists())
            passwordsFile.createNewFile();
        if(!rulesFile.exists()){
            rulesFile.createNewFile();
            FileConfiguration cfg = getConfiguration(rulesFile);
            List<String> list = new ArrayList<>();
            list.add("Dont scam!");
            list.add("Dont grief!");
            list.add("Dont Hack!");
            cfg.set("Rules", list);
            cfg.save(rulesFile);
        }
        if(!motdFile.exists()){
            motdFile.createNewFile();
            FileConfiguration cfg = getConfiguration(motdFile);
            cfg.set("Line.1", "&6Ein cooler Server");
            cfg.set("Line.2", "&2    Lust auf Craftattack?");
            cfg.save(motdFile);
        }
        if(!statusFile.exists()){
            statusFile.createNewFile();
            FileConfiguration cfg = getConfiguration(statusFile);
            cfg.set("Status.BraceColor", "§8");
            cfg.set("Status.1.Show", true);
            cfg.set("Status.1.Slot", 19);
            cfg.set("Status.1.Name", "LIVE");
            cfg.set("Status.1.DisplayName", "§7» §cLIVE");
            List<String> lore1 = new ArrayList<>();
            lore1.add("§7Klicke hier um deinen");
            lore1.add("§7Status auf Live zu setzen");
            cfg.set("Status.1.Lore", lore1);
            cfg.set("Status.1.Texture", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR" +
                    "1cmUvNDZiZTY1ZjQ0Y2QyMTAxNGM4Y2RkZDAxNThiZjc1MjI3YWRjYjFmZDE3OWY0YzFhY2QxNThjODg4NzFhMTNmIn19fQ==");
            cfg.set("Status.2.Show", true);
            cfg.set("Status.2.Slot", 25);
            cfg.set("Status.2.Name", "REC");
            cfg.set("Status.2.DisplayName", "§7» §cREC");
            List<String> lore2 = new ArrayList<>();
            lore2.add("§7Klicke hier um deinen");
            lore2.add("§7Status auf Rec zu setzen");
            cfg.set("Status.2.Lore", lore2);
            cfg.set("Status.2.Texture", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZ" +
                    "nQubmV0L3RleHR1cmUvZTY3OWQ2MzBmODUxYzU4OTdkYTgzYTY0MjUxNzQzM2Y2NWRjZmIzMmIxYmFiYjFmZWMzMmRhNzEyNmE5ZjYifX19");
            cfg.set("Status.3.Show", false);
            cfg.set("Status.3.Slot", 0);
            cfg.set("Status.3.Name", "Example");
            cfg.set("Status.3.DisplayName", "§7» §bExample");
            List<String> lore3 = new ArrayList<>();
            lore3.add("§7Klicke hier um deinen");
            lore3.add("§7Status auf Example1 zu setzen");
            cfg.set("Status.3.Lore", lore3);
            cfg.set("Status.3.Texture", "enter your texture here");
            cfg.set("Status.4.Show", false);
            cfg.set("Status.4.Slot", 0);
            cfg.set("Status.4.Name", "Example");
            cfg.set("Status.4.DisplayName", "§7» §bExample");
            List<String> lore4 = new ArrayList<>();
            lore4.add("§7Klicke hier um deinen");
            lore4.add("§7Status auf Example2 zu setzen");
            cfg.set("Status.4.Lore", lore4);
            cfg.set("Status.4.Texture", "enter your texture here");
            cfg.set("Status.5.Show", false);
            cfg.set("Status.5.Slot", 0);
            cfg.set("Status.5.Name", "Example");
            cfg.set("Status.5.DisplayName", "§7» §bExample");
            List<String> lore5 = new ArrayList<>();
            lore5.add("§7Klicke hier um deinen");
            lore5.add("§7Status auf Example3 zu setzen");
            cfg.set("Status.5.Lore", lore5);
            cfg.set("Status.5.Texture", "enter your texture here");
            cfg.set("Status.6.Show", false);
            cfg.set("Status.6.Slot", 0);
            cfg.set("Status.6.Name", "Example");
            cfg.set("Status.6.DisplayName", "§7» §bExample");
            List<String> lore6 = new ArrayList<>();
            lore6.add("§7Klicke hier um deinen");
            lore6.add("§7Status auf Example4 zu setzen");
            cfg.set("Status.6.Lore", lore6);
            cfg.set("Status.6.Texture", "enter your texture here");
            cfg.set("Status.7.Show", false);
            cfg.set("Status.7.Slot", 0);
            cfg.set("Status.7.Name", "Example");
            cfg.set("Status.7.DisplayName", "§7» §bExample");
            List<String> lore7 = new ArrayList<>();
            lore7.add("§7Klicke hier um deinen");
            lore7.add("§7Status auf Example5 zu setzen");
            cfg.set("Status.7.Lore", lore7);
            cfg.set("Status.7.Texture", "enter your texture here");
            cfg.set("Status.8.Show", false);
            cfg.set("Status.8.Slot", 0);
            cfg.set("Status.8.Name", "Example");
            cfg.set("Status.8.DisplayName", "§7» §bExample");
            List<String> lore8 = new ArrayList<>();
            lore8.add("§7Klicke hier um deinen");
            lore8.add("§7Status auf Example6 zu setzen");
            cfg.set("Status.8.Lore", lore8);
            cfg.set("Status.8.Texture", "enter your texture here");
            cfg.set("Status.9.Show", false);
            cfg.set("Status.9.Slot", 0);
            cfg.set("Status.9.Name", "Example");
            cfg.set("Status.9.DisplayName", "§7» §bExample");
            List<String> lore9 = new ArrayList<>();
            lore9.add("§7Klicke hier um deinen");
            lore9.add("§7Status auf Example7 zu setzen");
            cfg.set("Status.9.Lore", lore9);
            cfg.set("Status.9.Texture", "enter your texture here");
            cfg.save(statusFile);
        }
        if(!chatFile.exists()){
            chatFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(chatFile);
            cfg.set("Use", true);
            cfg.set("Format", "&8[&7%status%&8] &7%player% &8» &7%message%");
            cfg.save(chatFile);
        }

    }

    public static void delAllFiles(){
        File ordner = new File("plugins//Craftattack8");
        File configFile = new File("plugins//Craftattack8//config.yml");
        File messagesFile = new File("plugins//Craftattack8//messages.yml");
        File permissionsFile = new File("plugins//Craftattack8//permissions.yml");
        File profilesOrdner = new File("plugins//Craftattack8//Profiles");
        File teamsFile = new File("plugins//Craftattack8//teams.yml");

        ordner.delete();
        configFile.delete();
        messagesFile.delete();
        permissionsFile.delete();
        for (File file : profilesOrdner.listFiles()) {
            file.delete();
        }
        profilesOrdner.delete();
        teamsFile.delete();
    }

    public static void setMessage(String key, String msg) throws IOException {
        File file = new File("plugins//Craftattack8//messages.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Message." + key, msg);
        cfg.save(file);
    }

    public static String getMessage(String key){
        File file = new File("plugins//Craftattack8//messages.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return Strings.replaceAll(cfg.getString("Message." + key));
    }

    public static void setPermissionValue(String key, String perm) throws IOException {
        File file = new File("plugins//Craftattack8//permissions.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Permission." + key, perm);
        cfg.save(file);
    }

    public static String getPermissionValue(String key){
        File file = new File("plugins//Craftattack8//permissions.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getString("Permission." + key);
    }

    public static void setConfigValue(String key, String value) throws IOException {
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(key, value);
        cfg.save(file);
    }

    public static String getConfigValue(String key){
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getString(key);
    }

    public static void setConfigBool(String key, boolean value) throws IOException {
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(key, value);
        cfg.save(file);
    }

    public static boolean getConfigBool(String key){
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getBoolean(key);
    }

    public static void setConfigInt(String key, int value) throws IOException {
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(key, value);
        cfg.save(file);
    }

    public static int getConfigInt(String key){
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getInt(key);
    }

    public static void setConfigList(String key, List<String> list) throws IOException {
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(key, list);
        cfg.save(file);
    }

    public static List<String> getConfigList(String key){
        File file = new File("plugins//Craftattack8//config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return (List<String>) cfg.getList(key);
    }

    public static void setLocation(String name, Location loc) throws IOException {
        File file = new File("plugins//Craftattack8//locations.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(name + ".World", loc.getWorld().getName());
        cfg.set(name + ".X", loc.getX());
        cfg.set(name + ".Y", loc.getY());
        cfg.set(name + ".Z", loc.getZ());
        cfg.set(name + ".Yaw", loc.getYaw());
        cfg.set(name + ".Pitch", loc.getPitch());
        cfg.save(file);
    }

    public static Location getLocation(String name){
        File file = new File("plugins//Craftattack8//locations.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if(cfg.getString(name + ".World") != null) {
            float Yaw = (float) cfg.getDouble(name + ".Yaw");
            float Pitch = (float) cfg.getDouble(name + ".Pitch");
            World world = Bukkit.getWorld(cfg.getString(name + ".World"));

            Location loc = new Location(world, cfg.getDouble(name + ".X")
                    , cfg.getDouble(name + ".Y"), cfg.getDouble(name + ".Z")
                    , Yaw, Pitch);

            return loc;
        }else
            return null;

    }

    public static PlayerProfile getPlayerProfile(String name){
        File file = new File("plugins//Craftattack8//Profiles//" + name + ".yml");
        if(file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            PlayerProfile pp = new PlayerProfile(cfg.getString("Profile.FileName"), cfg.getString("Profile.DisplayName")
                    , cfg.getString("Profile.UUID"), cfg.getInt("Profile.Kills")
                    , cfg.getInt("Profile.Deaths"), cfg.getString("Profile.Status"));
            return pp;
        }else
            return null;
    }

    public static void setPlayerProfile(PlayerProfile pp) throws IOException {
        File file = new File("plugins//Craftattack8//Profiles//" + pp.getFileName());
        if(!file.exists()){
            file.createNewFile();
        }
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Profile.FileName", pp.getFileName());
        cfg.set("Profile.DisplayName", pp.getDisplayName());
        cfg.set("Profile.UUID", pp.getUuid());
        cfg.set("Profile.Kills", pp.getKills());
        cfg.set("Profile.Deaths", pp.getDeaths());
        cfg.set("Profile.Status", pp.getStatus());
        cfg.save(file);
    }

    public static void setStatus(String status, Player p) throws IOException {
        PlayerProfile pp = getPlayerProfile(p.getName());
            pp.setStatus(status);
            setPlayerProfile(pp);
    }

    public static String getStatus(Player p){
        PlayerProfile pp = getPlayerProfile(p.getName());
        if(pp.getStatus() != ""){
            return pp.getStatus();
        }else
            return "§cKein Status";
    }

    public static String getUuidByName(String name){
        return getPlayerProfile(name).getUuid();
    }

    public static String getNameByUUID(String uuid){
        File ordner = new File("plugins//Craftattack8//Profiles");
        String name = "";
        for(File file : ordner.listFiles()){
            if(getPlayerProfile(file.getName().replace(".yml", "")).getUuid().equals(uuid)){
                name = file.getName().replace(".yml", "");
            }
        }
        return name;
    }

    public static void setRegisteredPlayers(List<String> uuidList) throws IOException {
        FileConfiguration cfg = getConfiguration(getFile("Data//data.yml"));
        cfg.set("RegisteredPlayers", uuidList);
        cfg.save(getFile("Data//data.yml"));
    }

    public static List<String> getRegisteredPlayers(){
        return (List<String>) getConfiguration(getFile("Data//data.yml")).getList("RegisteredPlayers");
    }

    public static void addRegisteredPlayer(String uuid) throws IOException {
        List<String> list = getRegisteredPlayers();
        list.add(uuid);
        setRegisteredPlayers(list);
    }

    public static void removeRegisteredPlayer(String uuid) throws IOException {
        List<String> list = getRegisteredPlayers();
        list.remove(uuid);
        setRegisteredPlayers(list);
    }

    public static void setIngamePlayers(List<String> uuidList) throws IOException {
        FileConfiguration cfg = getConfiguration(getFile("Data//data.yml"));
        cfg.set("IngamePlayers", uuidList);
        cfg.save(getFile("Data//data.yml"));
    }

    public static List<String> getIngamePlayers(){
        return (List<String>) getConfiguration(getFile("Data//data.yml")).getList("IngamePlayers");
    }

    public static void addIngamePlayer(String uuid) throws IOException {
        List<String> list = getIngamePlayers();
        list.add(uuid);
        setIngamePlayers(list);
    }

    public static void removeIngamePlayer(String uuid) throws IOException {
        List<String> list = getIngamePlayers();
        list.remove(uuid);
        setIngamePlayers(list);
    }

    public static void setPassword(String uuid, String password) throws IOException {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getFile("Data//passwords.yml"));
        cfg.set(uuid, password);
        cfg.save(getFile("Data//passwords.yml"));

    }

    public static String getPassword(String uuid){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getFile("Data//passwords.yml"));
        if(cfg.getString(uuid) != null) {
            return cfg.getString(uuid);
        }else
            return null;
    }

    public static List<String> getRules(){
        File file = new File("plugins//Craftattack8//rules.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return (List<String>) cfg.getList("Rules");
    }

    public static void addRule(String value) throws IOException {
        File file = new File("plugins//Craftattack8//rules.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> list = (List<String>) cfg.getList("Rules");
        list.add(value);
        cfg.set("Rules", list);
        cfg.save(file);
    }

    public static String getMotd(int line){
        File file = new File("plugins//Craftattack8//motd.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getString("Line." + line);
    }

    public static FileConfiguration getStatus(){
        File file = new File("plugins//Craftattack8//status.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg;
    }

    public static FileConfiguration getChat(){
        File file = new File("plugins//Craftattack8//chat.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg;
    }

}
