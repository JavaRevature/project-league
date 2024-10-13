package com.revature.models;

public class Summoner {
    private int summoner_id;
    private String summoner_name;
    private int summoner_level;

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
}
