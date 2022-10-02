package dev.lupluv.ca8.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameTags {

    private static NameTags instance;
    private Scoreboard scoreboard;

    public NameTags(){
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    public void set(Player p, String prefix, String suffix){
        String s = p.getUniqueId().toString().substring(1, 6);

        Team team = null;

        if(scoreboard.getTeam(s) == null){
            team = scoreboard.registerNewTeam(s);
        }else{
            team = scoreboard.getTeam(s);
        }

        if(prefix != null) team.setPrefix(prefix);
        if(suffix != null) team.setSuffix(suffix);

        team.setColor(ChatColor.GRAY);

        team.addPlayer(p);

    }

    public void setPrefix(Player p, String prefix){
        String s = p.getUniqueId().toString().substring(1, 6);

        Team team = null;

        if(scoreboard.getTeam(s) == null){
            team = scoreboard.registerNewTeam(s);
        }else{
            team = scoreboard.getTeam(s);
        }

        if(prefix != null) team.setPrefix(prefix);

        team.setColor(ChatColor.GRAY);

        team.addPlayer(p);

    }

    public void setSuffix(Player p, String suffix){
        String s = p.getUniqueId().toString().substring(1, 6);

        Team team = null;

        if(scoreboard.getTeam(s) == null){
            team = scoreboard.registerNewTeam(s);
        }else{
            team = scoreboard.getTeam(s);
        }

        if(suffix != null) team.setSuffix(suffix);

        team.setColor(ChatColor.GRAY);

        team.addPlayer(p);

    }

    public static NameTags getInstance(){
        if(instance == null){
            instance = new NameTags();
        }
        return instance;
    }


}
