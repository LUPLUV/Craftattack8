package dev.lupluv.ca8.utils;

import io.papermc.paper.adventure.AdventureComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Tab {

    public static void update(Player p){

        PlayerProfile pp = FileManager.getPlayerProfile(p.getName());
        if(!pp.getStatus().equalsIgnoreCase("none")){
            NameTags.getInstance().set(p, FileManager.getStatus().getString("Status.BraceColor") + "[§7" + pp.getStatus().replace("&", "§") + FileManager.getStatus().getString("Status.BraceColor") + "] §f", null);
        }else
            NameTags.getInstance().set(p, "§7", null);

        if(FileManager.getConfigBool("Options.Features.UseTabListSupport")) {
            sendTab(p);
        }

    }

    public static void sendTab(Player p){
        /*
        String header = FileManager.getMessage("TabListHeader");
        String footer = FileManager.getMessage("TabListFooter");

        ServerGamePacketListenerImpl playerConnection = ((CraftPlayer)p).getHandle().connection;

        AdventureComponent ac = new AdventureComponent(Component.text(1, TextColor.color(255, 255, 255)));

        Ic headerToBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}");
        IChatBaseComponent footerToBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(headerToBaseComponent, footerToBaseComponent);

        playerConnection.send(packet);

        playerConnection.sendPacket(packet);
         */

    }

}
