package com.revature.DAOs;

import com.revature.models.Champion;
import java.sql.SQLException;
import java.util.List;

public interface SummonerChampionDAOInterface {
    List<Champion> getChampionsBySummonerId(int summonerId);
    int addChampionForSummoner(int summonerId, int championId) throws SQLException;
    int removeAllChampionFromSummoner(int summonerId);

}
