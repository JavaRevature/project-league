package com.revature.models;

public class Summoner {
    private int summoner_id;
    private String summoner_name;
    private int summoner_level;
    private String summoner_password;

    public String getSummoner_password() {
        return summoner_password;
    }

    public void setSummoner_password(String summoner_password) {
        this.summoner_password = summoner_password;
    }

    public Summoner(int summoner_id, String summoner_name, int summoner_level, String summoner_password) {
        this.summoner_id = summoner_id;
        this.summoner_name = summoner_name;
        this.summoner_level = summoner_level;
        this.summoner_password = summoner_password;
    }

    public Summoner(String summoner_name, int summoner_level) {
        this.summoner_name = summoner_name;
        this.summoner_level = summoner_level;
    }

    public Summoner() {
    }

    public int getSummoner_id() {
        return summoner_id;
    }

    public void setSummoner_id(int summoner_id) {
        this.summoner_id = summoner_id;
    }

    public String getSummoner_name() {
        return summoner_name;
    }

    public void setSummoner_name(String summoner_name) {
        this.summoner_name = summoner_name;
    }

    public int getSummoner_level() {
        return summoner_level;
    }

    public void setSummoner_level(int summoner_level) {
        this.summoner_level = summoner_level;
    }

    @Override
    public String toString() {
        return "Summoner{" +
            "summoner_id=" + summoner_id +
            ", summoner_name='" + summoner_name + '\'' +
            ", summoner_level=" + summoner_level +
            '}';
    }
}
