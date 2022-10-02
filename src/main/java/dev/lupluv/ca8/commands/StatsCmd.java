package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.InventoryManager;
import dev.lupluv.ca8.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(!p.hasPermission(FileManager.getPermissionValue("stats"))){
                p.sendMessage(Strings.getNoPerms());
            }else{
                if(args.length == 0){
                    p.openInventory(InventoryManager.getStatsMainInventory(1));
                }
            }
        }

        return false;
    }
}
