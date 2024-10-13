package com.revature.DAOs;

import com.revature.models.Summoner;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SummonerDAO implements SummonerDAOInterface{

    @Override
    public Summoner getSummonerById(int summoner_id) {
        try(Connection connection = ConnectionUtil.getConnection()){

        }catch (SQLException sqlException){

        };

        return null;
    }

    @Override
    public List<Summoner> getAllSummoners() {
        return List.of();
    }

    @Override
    public int addSummoner(Summoner summoner) {
        return 0;
    }

    @Override
    public int updateSummonerLevel(int level) {
        return 0;
    }

    @Override
    public int deleteSummoner(int summoner_id) {
        return 0;
    }
}
