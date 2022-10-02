package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.Strings;
import dev.lupluv.ca8.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Login implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(!p.hasPermission(FileManager.getPermissionValue("login"))){
                p.sendMessage(Strings.getNoPerms());
            }else
            if(!Util.loginedPlayers.contains(p)) {
                if(args.length == 1){
                    String pw = args[0];
                    if(FileManager.getPassword(p.getUniqueId().toString()) != null){
                            if (pw.equals(FileManager.getPassword(p.getUniqueId().toString()))) {
                                p.sendMessage(FileManager.getMessage("PasswordRight"));
                                Util.loginedPlayers.add(p);
                            } else {
                                if (pw.equalsIgnoreCase(FileManager.getPassword(p.getUniqueId().toString()))) {
                                    p.sendMessage(FileManager.getMessage("PasswordWrongCase"));
                                } else
                                    p.sendMessage(FileManager.getMessage("PasswordWrong"));
                            }
                    }else
                        p.sendMessage(FileManager.getMessage("NotRegistered"));
                }else
                    p.sendMessage(Strings.getCommandUsage("/login <password>"));
            }else
                p.sendMessage(FileManager.getMessage("AlreadyLoggedIn"));
        }else
            s.sendMessage(Strings.getNoConsoleCommand());

        return false;
    }
}
