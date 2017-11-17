package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private final String SCHEMA_PATH = "/db/schema.sql";
    
    public Database() {
        
    }
    
    public static void connect() throws ClassNotFoundException  {
        Connection conn = null;
        try {
            String driver = "org.sqlite.JDBC";
            Class.forName(driver);
            String dbName = "main.db";
            String dbUrl = "jdbc:sqlite:" +dbName;
            conn = DriverManager.getConnection(dbUrl);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
