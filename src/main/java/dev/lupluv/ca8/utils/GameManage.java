package dev.lupluv.ca8.utils;

import dev.lupluv.ca8.Ca8;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.IOException;

public class GameManage {

    private static int taskID;

    public static boolean countDownStarted = false;

    public static void start(){

        for(int i = 0; i < 100; i++){
            Bukkit.broadcastMessage(" ");
        }
        Bukkit.broadcastMessage(Strings.getPrefix() + "§b╔══════════ Rules ══════════╗");
        Bukkit.broadcastMessage(Strings.getPrefix() + " ");
        FileManager.getRules().forEach(rule ->{
            Bukkit.broadcastMessage(Strings.getPrefix() + "  §7» §b" + rule);
        });
        Bukkit.broadcastMessage(" ");
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Ca8.getPlugin(), new Runnable() {
                int i = 60;
                @Override
                public void run() {
                    if(i == 60 || i == 50 || i == 40 || i == 30 || i == 25 || i == 20 || i == 15 || i == 10 || i == 5 || i == 4 || i == 3 || i == 2 || i == 1) {
                        Bukkit.broadcastMessage(FileManager.getMessage("CountdownChat").replace("%seconds%", String.valueOf(i)));
                    }
                    if(i <= 5 && i != 0){
                        Bukkit.getOnlinePlayers().forEach(all ->{
                            all.sendTitle(FileManager.getMessage("CountdownTitleLine1").replace("%seconds%", String.valueOf(i))
                                    , FileManager.getMessage("CountdownTitleLine2").replace("%seconds%", String.valueOf(i)));
                        });
                    }
                    if(i == 0){
                        try {
                            FileManager.setConfigValue("GameState", "INGAME");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bukkit.getOnlinePlayers().forEach(all ->{
                            if(FileManager.getRegisteredPlayers().contains(all.getUniqueId().toString())) {
                                try {
                                    FileManager.addIngamePlayer(all.getUniqueId().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                all.teleport(FileManager.getLocation("spawn"));
                                all.getInventory().clear();
                                if(FileManager.getConfigBool("Options.Features.UseElytraSystem")) {
                                    giveStarterKit(all);
                                }
                                all.setGameMode(GameMode.SURVIVAL);
                                all.setLevel(0);
                                all.setExp(0);
                                all.getActivePotionEffects().clear();
                                all.setAllowFlight(false);
                                all.setHealth(20);
                                all.setFoodLevel(20);
                            }else{
                                all.teleport(FileManager.getLocation("spawn"));
                                all.getInventory().clear();
                                all.setGameMode(GameMode.SPECTATOR);
                                all.setLevel(0);
                                all.setExp(0);
                                all.getActivePotionEffects().clear();
                                all.setAllowFlight(true);
                                all.sendMessage(Strings.getPrefix() + "§cDu bist kein Teilnehmender Spieler und wirst daher in den Spectator Modus gesetzt!");
                            }
                        });
                        Bukkit.getScheduler().cancelTask(taskID);
                    }
                    i--;
                }
            }, 0, 20);
        }

    public static void abort(){

    }

    private static void sendAccept(){

        ComponentBuilder acceptBtn = new ComponentBuilder("§8[§a§lACCEPT§8]");
        ClickEvent click = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "rules accept");
        HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7» §aClick to accept the Rules!").create());
        acceptBtn.event(click);
        acceptBtn.event(hover);

    }

    public static void setScoreboard(Player p){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("abcd", "abcd");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(Strings.getPrefix());
        obj.getScore("Hi").setScore(0);
        p.setScoreboard(board);
    }

    public static void giveStarterKit(Player p){
        Item el = new Item(Material.ELYTRA);
        el.setDisplayName(FileManager.getMessage("ElytraName"));
        el.setShiny(true);
        el.setHideAttributes(true);
        el.setLore(Lore.create(FileManager.getMessage("ElytraLore")));
        p.getInventory().setChestplate(el.build());
        Item fireW = new Item(Material.FIREWORK_ROCKET, 16, (short) 0);
        fireW.setShiny(true);
        fireW.setHideAttributes(true);
        p.getInventory().setItem(0, fireW.build());
    }

}
