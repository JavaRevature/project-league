package com.revature.controllers;

//This controller is for handling authentication functionality

import com.revature.DAOs.AuthDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Summoner;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class AuthController {

    AuthDAO aDao = new AuthDAO();

    //HttpSession object to store user info after successful login
    //This object will be initialized upon successful login
    public static HttpSession session;


    //Login will be a post request, since we are sending a username and password(id and name)
    public Handler loginHandler = ctx -> {
        //Extract the request body as a LoginDTO
        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);

        //Use the loginDTO data to send to the DAO and try to log in!
        Summoner loggedInSummoner = aDao.login(loginDTO.getSummoner_name(), loginDTO.getSummoner_password());

        //The DAO will  either return a summoner or null
        if (loggedInSummoner != null) {
            //TODO: Create a session object
            //Create a session object
            session = ctx.req().getSession();
            session.setAttribute("summoner_id", loggedInSummoner.getSummoner_id());

            ctx.result("Welcome " + loggedInSummoner.getSummoner_name());
            ctx.status(200);

        } else{
            ctx.result("Login Failed");
            ctx.status(401);
            session = null;
            //If Login fails, a good status code is 401 - Unauthorized
        }
    };

}
