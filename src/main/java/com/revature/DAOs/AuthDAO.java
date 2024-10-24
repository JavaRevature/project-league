package com.revature.DAOs;

import com.revature.models.Summoner;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This dao will handle login
//We should have had username and password
public class AuthDAO {
    //TODO: Refactor summoner to user password
    public Summoner login(String summoner_name, String summoner_password) {
        //Open a connection
        try(Connection connection = ConnectionUtil.getConnection()){
            //Create our SQL String
            String sql = "SELECT * FROM summoners WHERE summoner_name = ? AND summoner_password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,summoner_name);
            ps.setString(2,summoner_password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Summoner(rs.getInt("summoner_id"),rs.getString("summoner_name"),rs.getInt("summoner_level"),rs.getString("summoner_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't log in user");
        }
        return null;
    }
}
