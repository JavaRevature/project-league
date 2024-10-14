package com.revature.DAOs;

import com.revature.models.Champion;
import java.util.List;

public interface ChampionDAOInterface {

    List<Champion> getAllChampion();

    Champion getChampionById(int champion_id);

    int addChampion(Champion champion);

    int updateChampion(Champion champion);

    int removeChampion(int champion_id);

}
