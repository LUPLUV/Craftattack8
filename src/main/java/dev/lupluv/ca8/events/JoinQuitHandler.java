package dev.lupluv.ca8.events;

import dev.lupluv.ca8.Ca8;
import dev.lupluv.ca8.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class JoinQuitHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();

        Bukkit.getScheduler().runTaskLater(Ca8.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(!Util.loginedPlayers.contains(p)) {
                    p.kickPlayer(FileManager.getMessage("LoginTimeoutKick"));
                }
            }
        }, 20*30);

        if(FileManager.getConfigBool("Options.Features.UseLoginSystem")) {
            if (FileManager.getPassword(p.getUniqueId().toString()) == null) {
                p.sendMessage(FileManager.getMessage("RegisterInfo"));
            } else {
                p.sendMessage(FileManager.getMessage("LoginInfo"));
            }
        }else{
            Util.loginedPlayers.add(p);
        }

        if(!FileManager.getRegisteredPlayers().contains(p.getUniqueId().toString())) {
            e.setJoinMessage(FileManager.getMessage("JoinMessage").replace("%player%", p.getName()) + " §c(Spectator)");
        }else{
            e.setJoinMessage(FileManager.getMessage("JoinMessage").replace("%player%", p.getName()));
        }
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("INGAME")){
        if(!FileManager.getRegisteredPlayers().contains(p.getUniqueId().toString())){
            p.getInventory().clear();
            p.setGameMode(GameMode.SPECTATOR);
            p.setLevel(0);
            p.setExp(0);
            p.getActivePotionEffects().clear();
            p.setAllowFlight(true);
            p.sendMessage(Strings.getPrefix() + "§cDu bist kein Teilnehmender Spieler und wirst daher in den Spectator Modus gesetzt!");
        }else{
            if(!FileManager.getIngamePlayers().contains(p.getUniqueId().toString())){
                try {
                    FileManager.addIngamePlayer(p.getUniqueId().toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                p.teleport(FileManager.getLocation("spawn"));
                p.getInventory().clear();
                if(FileManager.getConfigBool("Options.Features.UseElytraSystem")) {
                    GameManage.giveStarterKit(p);
                }
                p.setGameMode(GameMode.SURVIVAL);
                p.setLevel(0);
                p.setExp(0);
                p.getActivePotionEffects().clear();
                p.setAllowFlight(false);
                p.setHealth(20);
                p.setFoodLevel(20);
            }else{
                p.setGameMode(GameMode.SURVIVAL);
                p.setAllowFlight(false);
            }
        }
        }

        if(FileManager.getPlayerProfile(p.getName()) == null){
            PlayerProfile pp = new PlayerProfile(p.getName() + ".yml"
            , p.getName()
            , p.getUniqueId().toString()
            , 0
            , 0
            , "none");
            FileManager.setPlayerProfile(pp);
        }

        Tab.update(p);


        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("lobby")){
            p.setGameMode(GameMode.ADVENTURE);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setExp(0);
            p.setLevel(0);
            if(FileManager.getConfigBool("AllowLobbyFly"))
                p.setAllowFlight(true);
            if(FileManager.getLocation("lobby") != null){
                p.teleport(FileManager.getLocation("lobby"));
            }else{
                p.sendMessage(Strings.getPrefix() + " §cThe Lobby Location doesnt exists, when you think this is an error pls contact a Admin.");
            }
        }else
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("setup")){
            if(p.hasPermission(FileManager.getPermissionValue("setup"))){
                p.setGameMode(GameMode.CREATIVE);
                p.setAllowFlight(true);
                p.sendMessage(Strings.getPrefix() + " §cThe Server is in Setup, when you think you have all working, type /setup and click on the Wool " +
                        "to save the Setup and change to Lobby Mode!");
            }
        }else
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("ingame")){

        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(FileManager.getMessage("QuitMessage").replace("%player%", p.getName()));
    }

}
