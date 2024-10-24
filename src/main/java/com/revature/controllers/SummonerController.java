package com.revature.controllers;

import com.revature.DAOs.SummonerDAO;
import com.revature.models.Summoner;
import io.javalin.http.Handler;
import java.sql.SQLException;
import java.util.List;

/**
 * The Controller Layer is where HTTP requests, get sent after JAVAlin directs them from main()
 * It's in this layer that json comes in and gets translated to Java and vice versa
 * We will be talking Http requests from client and sending back responses
 */
public class SummonerController {

    SummonerDAO summonerDAO = new SummonerDAO();

    public Handler getAllSummonersHandler = ctx -> {
        List<Summoner> summoners = summonerDAO.getAllSummoners();

        //Problem we can't send plain java in a http response
        //Solution we can use the .json method() to convert this arraylist into json.

        ctx.json(summoners);

        ctx.status(200);
    };

    //This handler will handle post requests to the database
    public Handler insertSummonerHandler = ctx -> {
        //We have json coming in from the postman
        //We need to convert it to java
        //We can use ctx.bodyasClass(Summoner.class) to convert it
        Summoner newSummoner = ctx.bodyAsClass(Summoner.class);
        if(newSummoner.getSummoner_name().trim().isEmpty()){
            ctx.result("Summoner name are required");
            ctx.status(400);
        }
        if(newSummoner.getSummoner_level() >= 0){
          ctx.result("Summoner level must be greater than 0");
          ctx.status(400);
        }
        if(newSummoner.getSummoner_password().trim().isEmpty()){
            ctx.result("Summoner password are required");
            ctx.status(400);
        }
        try {
            Summoner summoner = summonerDAO.addSummoner(newSummoner);
            ctx.json(summoner);
            ctx.status(201);
        }catch (SQLException e){
            System.out.println("Catch Block Reached");
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler updateSummonerLevelHandler = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int newLevel = Integer.parseInt(ctx.body());

        if(newLevel <1){
            ctx.result("Summoner level must be greater than 1");
            ctx.status(400);
        }else {
            int returnedNewLevel = summonerDAO.updateSummonerLevel(id, newLevel);
            ctx.result("Updated salary for summoner with id " + id + " to " + + returnedNewLevel);
            ctx.status(200);
        }
    };

    public Handler deleteSummonerHandler = ctx -> {
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        int rowsDeleted = summonerDAO.deleteSummoner(id);
        ctx.result("Deleted " + rowsDeleted + " rows");
        ctx.status(200);
    };

}
