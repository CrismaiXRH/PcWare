package model;

import java.sql.*;
import java.sql.Driver;
import java.sql.DriverManager;

public class MySqlConnection {
    private static Connection con = null;
    private static final String url = "jdbc:mysql://localhost:3306/PcWare";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                e.printStackTrace();
                con = null;
            }
        }
        return con;
    }
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
