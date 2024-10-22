package com.revature.controllers;

import com.revature.DAOs.SummonerChampionDAO;
import io.javalin.http.Handler;
import com.revature.models.Champion;
import java.sql.SQLException;
import java.util.List;

public class SummonerChampionController {
    SummonerChampionDAO summonerChampionDAO = new SummonerChampionDAO();

    public Handler getChampionsBySummonerIdHandler = ctx -> {
        int id = (int)AuthController.session.getAttribute("summoner_id");
        List<Champion> champions = summonerChampionDAO.getChampionsBySummonerId(id);
        ctx.json(champions);
        ctx.status(200);
    };

    public Handler addChampionForSummonerHandler = ctx -> {
        int summonerId = (int)AuthController.session.getAttribute("summoner_id");
        int championId = Integer.parseInt(ctx.pathParam("championId"));
        try {
            summonerChampionDAO.addChampionForSummoner(summonerId, championId);
            ctx.status(201);
        } catch (SQLException e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }

    };
    public Handler deleteChampionForSummonerHandler = ctx -> {
        int summonerId = (int)AuthController.session.getAttribute("summoner_id");
        summonerChampionDAO.removeAllChampionFromSummoner(summonerId);
        ctx.status(204);

    };

    
}
