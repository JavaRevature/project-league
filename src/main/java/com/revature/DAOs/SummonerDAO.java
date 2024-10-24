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
    public Summoner addSummoner(Summoner summoner) throws SQLException{
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO summoners(summoner_name, summoner_level, summoner_password) VALUES(?,?,?) RETURNING *";
            if(!checkIfSummonerExists(summoner.getSummoner_name())) {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, summoner.getSummoner_name());
                ps.setInt(2,summoner.getSummoner_level());
                ps.setString(3,summoner.getSummoner_password());
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()){
                    return mapResultSetToSummoner(resultSet);
                }
            }
            else{
                throw new SQLException("Summoner name already exists");
            }

        } catch (SQLException e) {
            throw new SQLException("Summoner name already exists");

        }
        return null;
    }

    @Override
    public int updateSummonerLevel(int summoner_id, int summer_level) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE summoners SET summoner_level = ? WHERE summoner_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,summer_level);
            ps.setInt(2,summoner_id);
            ps.executeUpdate();
            return summer_level;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return -1;
    }

    @Override
    public int deleteSummoner(int summoner_id) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "DELETE FROM summoners WHERE summoner_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,summoner_id);
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return -1;
    }

    @Override
    public boolean checkIfSummonerExists(String summoner_name) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM summoners WHERE summoner_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,summoner_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return false;
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
