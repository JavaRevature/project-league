package com.revature;

import com.revature.DAOs.SummonerDAO;
import com.revature.models.Summoner;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {
        try(Connection connection = ConnectionUtil.getConnection()){
            System.out.println("Connection Successful");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception occurred");;
        }
        System.out.println("----All Summoner----");
        SummonerDAO summonerDAO = new SummonerDAO();
        List<Summoner> summoners = summonerDAO.getAllSummoners();
        for(Summoner summoner: summoners){
            System.out.println(summoner);
        }

        System.out.println("-----Single Summoner----");
        Summoner summoner1 = summonerDAO.getSummonerById(1);
        System.out.println(summoner1);
    }
}
