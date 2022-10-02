package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        if(s instanceof Player){
            Player p = (Player) s;
            if(args.length == 0){
                p.sendMessage(Strings.getPrefix() + "§b╔══════════ Rules ══════════╗");
                p.sendMessage(Strings.getPrefix() + " ");
                FileManager.getRules().forEach(rule ->{
                    p.sendMessage(Strings.getPrefix() + "  §7» §b" + rule);
                });
            }
        }

        return false;
    }
}
