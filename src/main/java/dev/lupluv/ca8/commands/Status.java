package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.InventoryManager;
import dev.lupluv.ca8.utils.Strings;
import dev.lupluv.ca8.utils.Tab;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Status implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(!p.hasPermission(FileManager.getPermissionValue("status"))){
                p.sendMessage(Strings.getNoPerms());
            }else
                if(args.length == 0){
                    p.openInventory(InventoryManager.getStatusInventory(p));
                }else
                if(args.length == 1){
                    if(!args[0].equalsIgnoreCase("reset")) {
                        String status = args[0].toUpperCase();
                        if(status.length() <= 4) {
                            try {
                                FileManager.setStatus(status, p);
                                Tab.update(p);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else
                            p.sendMessage(Strings.getPrefix() + "§cMax length = 4");
                    }else {
                        try {
                            FileManager.setStatus(null, p);
                            Tab.update(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        }else
            s.sendMessage(Strings.getNoConsoleCommand());

        return false;
    }
}
