package dev.lupluv.ca8.utils;

public class PlayerProfile {

    String fileName;
    String displayName;
    String uuid;
    int kills;
    int deaths;
    String status;
    String clan;

    public PlayerProfile(String fileName, String displayName, String uuid, int kills, int deaths, String status) {
        this.fileName = fileName;
        this.displayName = displayName;
        this.uuid = uuid;
        this.kills = kills;
        this.deaths = deaths;
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKill(){
        this.kills += 1;
    }

    public void addKills(int amount){
        this.kills += amount;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void addDeath(){
        this.deaths += 1;
    }

    public void addDeaths(int amount){
        this.deaths += amount;
    }

    public String getStatus() {
        if(status != null) {
            return status;
        }else
            return "";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }
}
