package com.revature.controllers;

import com.revature.DAOs.ChampionDAO;
import com.revature.models.Champion;
import io.javalin.http.Handler;
import java.sql.SQLException;

public class ChampionController {
    ChampionDAO championDAO = new ChampionDAO();

    public Handler getAllChampionHandler = ctx -> {
        ctx.json(championDAO.getAllChampion());
        ctx.status(200);
    };
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
        ctx.json(champion);
        ctx.status(200);
    };

    public Handler addChampionHandler = ctx -> {
        Champion champion = ctx.bodyAsClass(Champion.class);
        try {
            int newId = championDAO.addChampion(champion);
            Champion newChampion = championDAO.getChampionById(newId);
            ctx.json(newChampion);
            ctx.status(201);
        }catch (SQLException e){
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler deleteChampionHandler = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        championDAO.removeChampion(id);
        ctx.result("Champion " + id + " deleted");
        ctx.status(204);
    };



}
