package com.revature.DAOs;

import com.revature.models.Summoner;
import java.util.List;

public interface SummonerDAOInterface {

    //A method to get summoner information
    Summoner getSummonerById(int summoner_id);

    //A method to get all the summoner
    List<Summoner> getAllSummoners();

    int addSummoner(Summoner summoner);

    int updateSummonerLevel(Summoner summoner);

    int deleteSummoner(int summoner_id);


}
