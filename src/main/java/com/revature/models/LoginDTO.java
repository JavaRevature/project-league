package com.revature.models;

/**
What is a DTO? Data Transfer Object. It is meant to model some data that doesn't pertain a DB table.
 For instance, maybe we have login functionality that only takes username and password.

 */
public class LoginDTO {
    private String summoner_name;
    private String summoner_password;

    public LoginDTO(String summoner_name, String summoner_password) {
        this.summoner_name = summoner_name;
        this.summoner_password = summoner_password;
    }

    public LoginDTO() {
    }

    public String getSummoner_name() {
        return summoner_name;
    }

    public void setSummoner_name(String summoner_name) {
        this.summoner_name = summoner_name;
    }

    public String getSummoner_password() {
        return summoner_password;
    }

    public void setSummoner_password(String summoner_password) {
        this.summoner_password = summoner_password;
    }
}
