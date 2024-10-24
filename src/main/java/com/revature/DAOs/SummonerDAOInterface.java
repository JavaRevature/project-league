package com.revature.DAOs;

import com.revature.models.Summoner;
import java.sql.SQLException;
import java.util.List;

public interface SummonerDAOInterface {

    //A method to get summoner information
    Summoner getSummonerById(int summoner_id);

    //A method to get all the summoner
    List<Summoner> getAllSummoners();

    Summoner addSummoner(Summoner summoner) throws SQLException;

    int updateSummonerLevel(int summer_id,int summer_level);

    int deleteSummoner(int summoner_id);

    boolean checkIfSummonerExists(String summoner_name);

}
