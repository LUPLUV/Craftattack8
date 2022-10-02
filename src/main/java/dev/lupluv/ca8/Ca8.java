package dev.lupluv.ca8;

import dev.lupluv.ca8.commands.*;
import dev.lupluv.ca8.events.*;
import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Ca8 extends JavaPlugin {

    private static Ca8 main;

    @Override
    public void onEnable() {

        main = this;

        /* Messages */

        print("§2  _____ _____            ______ _______    _______ _______       _____ _  __         __ ");
        print("§2 / ____|  __ \\     /\\   |  ____|__   __|/\\|__   __|__   __|/\\   / ____| |/ /        /_ |");
        print("§2| |    | |__) |   /  \\  | |__     | |  /  \\  | |     | |  /  \\ | |    | ' /    __   _| |");
        print("§2| |    |  _  /   / /\\ \\ |  __|    | | / /\\ \\ | |     | | / /\\ \\| |    |  <     \\ \\ / / |");
        print("§2| |____| | \\ \\  / ____ \\| |       | |/ ____ \\| |     | |/ ____ \\ |____| . \\     \\ V /| |");
        print("§2 \\_____|_|  \\_\\/_/    \\_\\_|       |_/_/    \\_\\_|     |_/_/    \\_\\_____|_|\\_\\     \\_/ |_|");
        print("§7[§6CraftAttack§7] §aDas Plugin startet ...");

        /* Files */

        try {
            FileManager.loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Commands */

        getCommand("setup").setExecutor(new Setup());
        getCommand("craftattack").setExecutor(new Craftattack());
        getCommand("login").setExecutor(new Login());
        getCommand("register").setExecutor(new Register());
        getCommand("status").setExecutor(new Status());
        getCommand("rules").setExecutor(new Rules());
        getCommand("pwreset").setExecutor(new Pwreset());
        getCommand("options").setExecutor(new Options());
        getCommand("stats").setExecutor(new StatsCmd());


        /* Events */

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ClickHandler(), this);
        pm.registerEvents(new JoinQuitHandler(), this);
        pm.registerEvents(new MoveEvent(), this);
        pm.registerEvents(new PropertyHandler(), this);
        pm.registerEvents(new ChatEvent(), this);

        /* Messages */




        /* Dependencies */

        /* File file = new File("plugins//TablistAPI.jar");
           if(Bukkit.getPluginManager().getPlugin("NametagEdit") == null) {
               String link = "http://161.97.92.168/TablistAPI.jar";
               File out = new File("plugins//TablistAPI.jar");
               new Thread(new Download(link, out)).start();
           }
        */


        /* Gamerules */


        /* Util */

        for (Player p : Bukkit.getOnlinePlayers()) {
            if(FileManager.getConfigBool("Options.Features.UseLoginSystem")) {
                if (FileManager.getPassword(p.getUniqueId().toString()) == null) {
                    p.sendMessage(FileManager.getMessage("RegisterInfo"));
                } else {
                    p.sendMessage(FileManager.getMessage("LoginInfo"));
                }
            }else{
                Util.loginedPlayers.add(p);
            }
        }

        Util.startAutoBroadcast();

    }

    @Override
    public void onDisable() {

    }

    private static void print(String msg){
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    public static Ca8 getPlugin(){
        return main;
    }

}
