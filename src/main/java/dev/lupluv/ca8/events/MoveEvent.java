package dev.lupluv.ca8.events;

import dev.lupluv.ca8.utils.FileManager;
import dev.lupluv.ca8.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location to = e.getTo();
        if(!Util.loginedPlayers.contains(p))
            e.setCancelled(true);
        if(FileManager.getConfigValue("GameState").equalsIgnoreCase("ingame")){
            if(FileManager.getConfigBool("Options.Features.UseElytraSystem")) {
                if (FileManager.getRegisteredPlayers().contains(p.getUniqueId().toString())) {
                    if (p.getInventory().getChestplate() != null) {
                        if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(FileManager.getMessage("ElytraName"))) {
                            if (to.getY() < FileManager.getLocation("spawn").getY() - FileManager.getConfigInt("Ingame.SpawnProtectRadius")) {
                                Location locUnderPlayer = new Location(to.getWorld(), to.getX(), to.getY() - 1, to.getZ());
                                Block bUnderPlayer = locUnderPlayer.getBlock();
                                if (bUnderPlayer != null) {
                                    if (bUnderPlayer.getType() != Material.AIR) {
                                        p.getInventory().setChestplate(null);
                                        for (ItemStack stack : p.getInventory()) {
                                            if (stack != null) {
                                                if (stack.getType() == Material.FIREWORK_ROCKET) {
                                                    p.getInventory().remove(stack);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
