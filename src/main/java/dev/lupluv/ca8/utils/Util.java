package dev.lupluv.ca8.utils;

import com.destroystokyo.paper.profile.ProfileProperty;
import dev.lupluv.ca8.Ca8;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import com.destroystokyo.paper.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Util {

    public static List<Player> loginedPlayers = new ArrayList<>();

    public static ItemStack createCustomSkull(String value, String name, List<String> lore){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(value.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
        profile.setProperty(new ProfileProperty("textures", value));
        headMeta.setPlayerProfile(profile);
        headMeta.setDisplayName(name);
        headMeta.setLore(lore);
        head.setItemMeta(headMeta);
        return head;
    }

    public static void startAutoBroadcast(){
        Random r = new Random();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Ca8.getPlugin(), new Runnable() {
            @Override
            public void run() {

                if(FileManager.getConfigBool("Options.Features.UseAutoBroadcast")) {
                    List<String> msg = FileManager.getConfigList("AutoBroadCast.Messages");
                    Bukkit.broadcastMessage(Strings.getPrefix() + " §7" + msg.get(r.nextInt(msg.size())).replace("&", "§"));
                }

            }
        }, 0, 20*FileManager.getConfigInt("AutoBroadCast.SecondsBetweenEveryMessage"));
    }

}
