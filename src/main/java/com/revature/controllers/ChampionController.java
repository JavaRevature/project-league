package com.revature.controllers;

import com.revature.DAOs.ChampionDAO;
import com.revature.models.Champion;
import io.javalin.http.Handler;

public class ChampionController {
    ChampionDAO championDAO = new ChampionDAO();
    public Handler getChampionByIdHandler = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Champion champion = championDAO.getChampionById(id);
        if (champion == null) {
            ctx.result("Champion"+ id + " not found");
            ctx.status(404);
        } else if (id < 0) {
            ctx.result("Champion id must be greater than 0");
            ctx.status(400);
        } else {
            ctx.json(champion);
            ctx.status(200);
        }
    };

    public Handler updateChampionHandler = ctx -> {
        Champion champion = ctx.bodyAsClass(Champion.class);
        championDAO.updateChampion(champion);
        ctx.status(204);
    };

}
