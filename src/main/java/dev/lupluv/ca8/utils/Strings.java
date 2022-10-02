package dev.lupluv.ca8.utils;

public class Strings {

    public static String replaceAll(String toReplace){
        return toReplace.replace("&", "§").replace("%prefix%", getPrefix())
                .replace("%>>%", "»").replace("%<<%", "«");
    }

    public static String getPrefix(){
        String raw = FileManager.getConfigValue("Prefix");
        return raw.replace("&", "§").replace("%>>%", "»").replace("%<<%", "«");
    }

    public static String getNoPerms(){
        return FileManager.getMessage("NoPerms");
    }

    public static String getPlayerNotFound(String player){
        return FileManager.getMessage("PlayerNotFound").replace("%player%", player);
    }

    public static String getNoConsoleCommand(){
        return FileManager.getMessage("NoConsoleCommand");
    }

    public static String getCommandUsage(String command){
        return FileManager.getMessage("CommandUsage").replace("%command%", command);
    }

}
