package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.ChampionController;
import com.revature.controllers.SummonerChampionController;
import com.revature.controllers.SummonerController;
import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

public class LauncherJavalin {

    public static void main(String[] args) {
        SummonerController summonerController = new SummonerController();
        ChampionController championController = new ChampionController();
        SummonerChampionController summonerChampionController = new SummonerChampionController();
        AuthController authController = new AuthController();
        ConnectionUtil.resetDatabase();
        //Typical Javalin Setup Syntax
        var app = Javalin.create().start(7000);

        //Before Handler This is what's checking for a not null session to see if a user is logged in.

        app.before("/summoners/*",ctx ->{
            if(AuthController.session == null){
                ctx.status(401);
                ctx.result("You must be logged in to access this resource");
                throw new IllegalArgumentException("You must be logged in to access this resource");
            }
        });

        app.before("/summoners",ctx ->{
            if(AuthController.session == null){
                ctx.status(401);
                ctx.result("You must be logged in to access this resource");
                throw new IllegalArgumentException("You must be logged in to access this resource");
            }
        });

        app.before("/summoners-champions",ctx ->{
            if(AuthController.session == null){
                ctx.status(401);
                ctx.result("You must be logged in to access this resource");
                throw new IllegalArgumentException("You must be logged in to access this resource");
            }
        });

        app.before("/champions",ctx ->{
            if(AuthController.session == null){
                ctx.status(401);
                ctx.result("You must be logged in to access this resource");
                throw new IllegalArgumentException("You must be logged in to access this resource");
            }
        });

        app.before("/champions/*",ctx ->{
            if(AuthController.session == null){
                ctx.status(401);
                ctx.result("You must be logged in to access this resource");
                throw new IllegalArgumentException("You must be logged in to access this resource");
            }
        });



        app.exception(IllegalArgumentException.class,(e,ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });

        //Get all the summoners
        app.get("/summoners", summonerController.getAllSummonersHandler);

        app.post("/summoners", summonerController.insertSummonerHandler);

        app.get("/champions/{id}", championController.getChampionByIdHandler);

        app.patch("/summoners/{id}", summonerController.updateSummonerLevelHandler);

        app.delete("/summoners/{id}", summonerController.deleteSummonerHandler);

        app.get("/champions", championController.getAllChampionHandler);

        app.patch("/champions", championController.updateChampionHandler);

        app.delete("/champions/{id}", championController.deleteChampionHandler);

        app.post("/champions", championController.addChampionHandler);

        app.get("/summoners-champions", summonerChampionController.getChampionsBySummonerIdHandler);

        app.delete("/summoners-champions", summonerChampionController.deleteChampionForSummonerHandler);

        app.post("/summoners-champions/{championId}", summonerChampionController.addChampionForSummonerHandler);

        app.post("/login", authController.loginHandler);









    }
}
