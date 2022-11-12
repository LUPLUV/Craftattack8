package dev.lupluv.ca8.utils;

import org.bukkit.entity.Player;

public class Tab {

    public static void update(Player p){

        PlayerProfile pp = FileManager.getPlayerProfile(p.getName());
        if(!pp.getStatus().equalsIgnoreCase("none")){
            NameTags.getInstance().set(p, FileManager.getStatus().getString("Status.BraceColor") + "[§7" + pp.getStatus().replace("&", "§") + FileManager.getStatus().getString("Status.BraceColor") + "] §f", null);
        }else {
            NameTags.getInstance().set(p, "§7", null);
        }

        if(FileManager.getConfigBool("Options.Features.UseTabListSupport")) {
            sendTab(p);
        }

    }

    public static void sendTab(Player p){
        String header = FileManager.getMessage("TabListHeader");
        String footer = FileManager.getMessage("TabListFooter");
        p.setPlayerListHeaderFooter(header, footer);
    }

}
