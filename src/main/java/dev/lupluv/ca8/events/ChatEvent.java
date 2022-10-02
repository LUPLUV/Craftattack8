package dev.lupluv.ca8.events;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.PlayerProfile;
import dev.lupluv.ca8.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        String msg = e.getMessage();
        if(FileManager.getChat().getBoolean("Use")){
            PlayerProfile pp = FileManager.getPlayerProfile(p.getName());
            if(!pp.getStatus().equalsIgnoreCase("none")){
                Bukkit.broadcastMessage(Strings.replaceAll(FileManager.getChat().getString("Format").replace("%status%", FileManager.getStatus(p)).replace("%player%", p.getName()).replace("%message%", msg)));
            }else{
                Bukkit.broadcastMessage(Strings.replaceAll(FileManager.getChat().getString("Format").replace("%player%", p.getName()).replace("%message%", msg).replace("%status%", "")));
            }
            e.setCancelled(true);
        }
    }

}
