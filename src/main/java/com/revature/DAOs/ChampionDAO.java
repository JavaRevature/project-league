package com.revature.DAOs;

import com.revature.models.Champion;
import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChampionDAO implements ChampionDAOInterface {

    @Override
    public List<Champion> getAllChampion() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM champions;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Champion> championList = new ArrayList<>();

            while (resultSet.next()) {
                Champion champion = mapResultSetToChampion(resultSet);
                championList.add(champion);
            }

            return championList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return null;
    }

    @Override
    public Champion getChampionById(int champion_id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM champions WHERE champion_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, champion_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToChampion(resultSet);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return null;
    }

    @Override
    public int addChampion(Champion champion) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            if(!checkIfChampionExists(champion.getChampion_name())) {
                String sql = "INSERT INTO champions(champion_name, attack, defense, magic, difficulty) VALUES (?, ?, ?, ?, ?) RETURNING champion_id;";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, champion.getChampion_name());
                ps.setInt(2, champion.getAttack());
                ps.setInt(3, champion.getDefense());
                ps.setInt(4, champion.getMagic());
                ps.setInt(5, champion.getDifficulty());
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
                else {
                    throw new SQLException("Champion name already exists");
                }
            }
            else {
                throw new SQLException("Champion name already exists");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Champion name already exists");
        }
    }

    @Override
    public int updateChampion(Champion champion) {
        try (Connection connection = ConnectionUtil.getConnection()) {
                String sql = "UPDATE champions SET champion_name = ?, attack = ?, defense = ?, magic = ?, difficulty = ? WHERE champion_id = ?;";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, champion.getChampion_name());
                ps.setInt(2, champion.getAttack());
                ps.setInt(3, champion.getDefense());
                ps.setInt(4, champion.getMagic());
                ps.setInt(5, champion.getDifficulty());
                ps.setInt(6, champion.getChampion_id());
                return ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return -1;
    }

    @Override
    public int removeChampion(int champion_id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM champions WHERE champion_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, champion_id);
            return ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return -1;
    }

    private Champion mapResultSetToChampion(ResultSet resultSet) {
        try {
            Champion champion = new Champion();
            champion.setChampion_id(resultSet.getInt("champion_id"));
            champion.setChampion_name(resultSet.getString("champion_name"));
            champion.setAttack(resultSet.getInt("attack"));
            champion.setDefense(resultSet.getInt("defense"));
            champion.setMagic(resultSet.getInt("magic"));
            champion.setDifficulty(resultSet.getInt("difficulty"));
            return champion;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
    }
        return null;
    }

    private Boolean checkIfChampionExists(String champion_name) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM champions WHERE champion_name = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, champion_name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception caught");
        }
        return false;
    }
}