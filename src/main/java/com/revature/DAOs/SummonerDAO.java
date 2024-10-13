package com.revature.DAOs;

import com.revature.models.Summoner;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SummonerDAO implements SummonerDAOInterface{

    @Override
    public Summoner getSummonerById(int summoner_id) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM summoners WHERE summoner_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,summoner_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return mapResultSetToSummoner(rs);
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("SQL exception caught");
        };

        return null;
    }

    @Override
    public List<Summoner> getAllSummoners() {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM summoners";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<Summoner> summoners = new ArrayList<>();
            while(rs.next()){
                summoners.add(mapResultSetToSummoner(rs));
            }
            return summoners;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return null;
    }

    @Override
    public int addSummoner(Summoner summoner) {
        return 0;
    }

    @Override
    public int updateSummonerLevel(int level) {
        return 0;
    }

    @Override
    public int deleteSummoner(int summoner_id) {
        return 0;
    }

    Summoner mapResultSetToSummoner(ResultSet rs){

        Summoner summoner = new Summoner();
        try{
            summoner.setSummoner_id(rs.getInt("summoner_id"));
            summoner.setSummoner_level(rs.getInt("summoner_level"));
            summoner.setSummoner_name(rs.getString("summoner_name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return summoner;
    }
}
