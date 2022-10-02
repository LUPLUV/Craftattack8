package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Pwreset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(!p.hasPermission(FileManager.getPermissionValue("pwreset"))){
                p.sendMessage(Strings.getNoPerms());
            }else
                if(args.length == 1){
                    String password = args[0];
                    if(FileManager.getPassword(p.getUniqueId().toString()).equals(password)){
                        try {
                            FileManager.setPassword(p.getUniqueId().toString(), null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.kickPlayer(FileManager.getMessage("PwresetKick"));
                    }else
                        p.sendMessage(FileManager.getMessage("PasswordWrong"));
                }else
                    p.sendMessage(Strings.getCommandUsage("pwreset"));
        }else
            s.sendMessage(Strings.getNoConsoleCommand());

        return false;
    }
}
