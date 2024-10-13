package com.revature;

import com.revature.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {
        try(Connection connection = ConnectionUtil.getConnection()){
            System.out.println("Connection Successful");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception occurred");;
        }
    }
}
