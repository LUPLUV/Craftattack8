package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.GameManage;
import dev.lupluv.ca8.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Craftattack implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(!p.hasPermission(FileManager.getPermissionValue("craftattack"))){
                p.sendMessage(Strings.getNoPerms());
            }else{
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("start")) {
                        if (FileManager.getConfigValue("GameState").equalsIgnoreCase("lobby")) {
                            if (!GameManage.countDownStarted) {
                                p.sendMessage(Strings.getPrefix() + "§cYou have startet the Countdown!");
                                GameManage.start();
                            } else {
                                p.sendMessage(Strings.getPrefix() + "§cThe Countdown is already started, cancel it with /craftattack abort");
                            }
                        } else
                            p.sendMessage(Strings.getPrefix() + "§cThe Game has to be in Lobby Mode!");
                    }
                }else
                if(args.length == 3){
                    if(args[0].equalsIgnoreCase("registered") || args[0].equalsIgnoreCase("rp")){
                        if(args[1].equalsIgnoreCase("add")){
                            OfflinePlayer t = Bukkit.getOfflinePlayer(args[2]);
                            if(t != null) {
                                try {
                                    FileManager.addRegisteredPlayer(t.getUniqueId().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                p.sendMessage(Strings.getPrefix() + "§cYou have added " + t.getName() + " to the Registered Players.");
                            }else
                                p.sendMessage(Strings.getPlayerNotFound(t.getName()));
                        }else
                        if(args[1].equalsIgnoreCase("remove")){
                            OfflinePlayer t = Bukkit.getOfflinePlayer(args[2]);
                            if(t != null) {
                                try {
                                    FileManager.removeRegisteredPlayer(t.getUniqueId().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                p.sendMessage(Strings.getPrefix() + "§cYou have removed " + t.getName() + " from the Registered Players.");
                            }else
                                p.sendMessage(Strings.getPlayerNotFound(t.getName()));
                        }
                    }else if(args[0].equalsIgnoreCase("ingame") || args[0].equalsIgnoreCase("ig")){
                        if(args[1].equalsIgnoreCase("add")){
                            OfflinePlayer t = Bukkit.getOfflinePlayer(args[2]);
                            if(t != null) {
                                try {
                                    FileManager.addIngamePlayer(t.getUniqueId().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                p.sendMessage(Strings.getPrefix() + "§cYou have added " + t.getName() + " to the Registered Players.");
                            }else
                                p.sendMessage(Strings.getPlayerNotFound(t.getName()));
                        }else
                        if(args[1].equalsIgnoreCase("remove")){
                            OfflinePlayer t = Bukkit.getOfflinePlayer(args[2]);
                            if(t != null) {
                                try {
                                    FileManager.removeIngamePlayer(t.getUniqueId().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                p.sendMessage(Strings.getPrefix() + "§cYou have removed " + t.getName() + " from the Registered Players.");
                            }else
                                p.sendMessage(Strings.getPlayerNotFound(t.getName()));
                        }
                    }
                }
            }
        }else
            s.sendMessage(Strings.getNoConsoleCommand());

        return false;
    }
}
