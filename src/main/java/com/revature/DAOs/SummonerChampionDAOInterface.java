package com.revature.DAOs;

import com.revature.models.Champion;
import java.util.List;

public interface SummonerChampionDAOInterface {
    List<Champion> getChampionsBySummonerId(int summonerId);
    int addChampionForSummoner(int summonerId, int championId);
    int removeAllChampionFromSummoner(int summonerId);

}
