package com.revature;

import com.revature.DAOs.ChampionDAO;
import com.revature.DAOs.SummonerChampionDAO;
import com.revature.DAOs.SummonerDAO;
import com.revature.models.Champion;
import com.revature.models.Summoner;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Launcher {

    public static void main(String[] args) {
        setup();
        //summonersTest();
        //championsTest();
        summonersChampionsDAOTest();
    }

    public static void setup() {
        try(Connection connection = ConnectionUtil.getConnection()){
            System.out.println("Connection Successful");
            System.out.println(connection);
            ConnectionUtil.resetDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception occurred");;
        }
    };

    public static void summonersTest(){
        System.out.println("----Get All Summoner----");
        SummonerDAO summonerDAO = new SummonerDAO();
        List<Summoner> summoners = summonerDAO.getAllSummoners();
        for(Summoner summoner: summoners){
            System.out.println(summoner);
        }

        System.out.println("-----Get Single Summoner----");
        Summoner summoner1 = summonerDAO.getSummonerById(1);
        System.out.println(summoner1);

        System.out.println("-----Add Summoner-----");
        Summoner summoner = new Summoner("New Summoner",1);
        //int numbersOfRowsAffected = summonerDAO.addSummoner(summoner);
        //System.out.println(numbersOfRowsAffected + " row was affected.");
        System.out.println("-----New Summoner-----");
        summoners = summonerDAO.getAllSummoners();
        System.out.println(summoners.get(summoners.size()-1));

        System.out.println("----Update Summoner----");
        Summoner summonerToBeUpdated =summoners.get(summoners.size()-1);
        summonerToBeUpdated.setSummoner_level(200);
        //numbersOfRowsAffected = summonerDAO.updateSummonerLevel(summonerToBeUpdated);
       // System.out.println(numbersOfRowsAffected + " row was affected.");
        summoners = summonerDAO.getAllSummoners();
        System.out.println(summoners.get(summoners.size()-1));

        System.out.println("----Delete Summoner----");
        int numbersOfRowsAffected = summonerDAO.deleteSummoner(summoners.size());
        System.out.println(numbersOfRowsAffected + " row was affected.");
        summoners = summonerDAO.getAllSummoners();
        for(Summoner s: summoners){
            System.out.println(s);
        }
    }

    public static void championsTest(){

        ChampionDAO championDAO = new ChampionDAO();
        System.out.println("----Get All Champions----");
        List<Champion> champions = championDAO.getAllChampion();
        for(Champion champion: champions){
            System.out.println(champion);
        }

        System.out.println("----Get Single Champion----");
        Champion champion1 = championDAO.getChampionById(1);
        System.out.println(champion1);

        System.out.println("----Add Champion----");
        Champion champion = new Champion("New Champion",1,1,1,1);
        int numbersOfRowsAffected = championDAO.addChampion(champion);
        System.out.println(numbersOfRowsAffected + " row was affected.");
        System.out.println("----New Champion----");
        champions = championDAO.getAllChampion();
        System.out.println(champions.get(champions.size()-1));

        System.out.println("----Update Champion----");
        Champion championToBeUpdated =champions.get(champions.size()-1);
        championToBeUpdated.setAttack(5);
        numbersOfRowsAffected = championDAO.updateChampion(championToBeUpdated);
        System.out.println(numbersOfRowsAffected + " row was affected.");
        champions = championDAO.getAllChampion();
        System.out.println(champions.get(champions.size()-1));

        System.out.println("----Delete Champion----");
        numbersOfRowsAffected = championDAO.removeChampion(champions.size());
        System.out.println(numbersOfRowsAffected + " row was affected.");
        champions = championDAO.getAllChampion();
        for(Champion c: champions){
            System.out.println(c);
        }

    }

    public static void summonersChampionsDAOTest() {
        System.out.println("----Get all champions from a summoner----");
        SummonerChampionDAO summonerChampionDAO = new SummonerChampionDAO();
        List<Champion> champions = summonerChampionDAO.getChampionsBySummonerId(3);
        for (Champion champion : champions) {
            System.out.println(champion);
        }

        System.out.println("----Add champion to a summoner----");
        int numbersOfRowsAffected = summonerChampionDAO.addChampionForSummoner(3, 1);
        System.out.println(numbersOfRowsAffected + " row was affected.");
        champions = summonerChampionDAO.getChampionsBySummonerId(3);
        for (Champion champion : champions) {
            System.out.println(champion);
        }

        System.out.println("----Delete champion from a summoner----");
        numbersOfRowsAffected = summonerChampionDAO.removeAllChampionFromSummoner(3);
        System.out.println(numbersOfRowsAffected + " row was affected.");
    }
}
