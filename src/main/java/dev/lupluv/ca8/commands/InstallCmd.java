package dev.lupluv.ca8.commands;

import dev.lupluv.ca8.utils.Download;
import dev.lupluv.ca8.utils.Spiget;
import dev.lupluv.ca8.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class InstallCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!p.hasPermission("ca8.install")){
                p.sendMessage(Strings.getNoPerms());
            }else{
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("sgm")){
                        String url = Spiget.getLatestDownload("86743", "SimpleGamemode", "SimpleGamemode", "");
                        if(Bukkit.getPluginManager().getPlugin("SimpleGamemode") == null) {
                            String link = url;
                            File out = new File("plugins//SimpleGamemode.jar");
                            new Thread(new Download(link, out)).start();
                        }
                    }
                }
            }
        }

        return false;
    }
}
