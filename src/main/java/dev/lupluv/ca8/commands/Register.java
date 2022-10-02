package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.Strings;
import dev.lupluv.ca8.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Register implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(!p.hasPermission(FileManager.getPermissionValue("register"))){
                p.sendMessage(Strings.getNoPerms());
            }else
            if(FileManager.getPassword(p.getUniqueId().toString()) == null){
            if(args.length == 2){
                String pw1 = args[0];
                String pw2 = args[1];

                    if(pw1.equals(pw2)){
                        p.sendMessage(FileManager.getMessage("RegisterRight"));
                        try {
                            FileManager.setPassword(p.getUniqueId().toString(), pw1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Util.loginedPlayers.add(p);
                    }else{
                        p.sendMessage(FileManager.getMessage("RegisterPasswordsNotSame"));
                    }
            }else
                p.sendMessage(Strings.getCommandUsage("/register <password> <password>"));
            }else
                p.sendMessage(FileManager.getMessage("AlreadyRegistered"));
        }else
            s.sendMessage(Strings.getNoConsoleCommand());

        return false;
    }
}
