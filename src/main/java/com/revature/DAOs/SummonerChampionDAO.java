package com.revature.DAOs;

import com.revature.models.Champion;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SummonerChampionDAO implements SummonerChampionDAOInterface {
    private ChampionDAO championDAO = new ChampionDAO();
    @Override
    public List<Champion> getChampionsBySummonerId(int summonerId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM summoners_champions WHERE summoner_id_fk = ?;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, summonerId);
            ResultSet rs = ps.executeQuery();

            ArrayList<Champion> champions = new ArrayList<Champion>();

            while (rs.next()) {
                champions.add(championDAO.getChampionById(rs.getInt("champion_id_fk")));
            }

            return champions;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception occurred");
        }
        return null;
    }

    @Override
    public int addChampionForSummoner(int summonerId, int championId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO summoners_champions (summoner_id_fk, champion_id_fk) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, summonerId);
            ps.setInt(2, championId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Champion already owned by summoner;");
        }
    }

    @Override
    public int removeAllChampionFromSummoner(int summonerId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM summoners_champions WHERE summoner_id_fk = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, summonerId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception occurred");
        }
        return -1;
    }
}
