package Data;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {


    private final String driver = "org.sqlite.JDBC";
    private String databaseAddress;

 
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);
        return DriverManager.getConnection(databaseAddress);
    }

    public void connect() {
        try (Connection connection = getConnection()) {
            Statement st = connection.createStatement();

            //You may remove these
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            String query = "SELECT * FROM Item";
            
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            System.out.println(rs.getString("title"));
            

        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }

}
