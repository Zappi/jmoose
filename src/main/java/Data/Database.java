package Data;

import java.sql.*;

public class Database {

    private final String driver = "org.sqlite.JDBC";
    private String databaseAddress;

    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);
        return DriverManager.getConnection(databaseAddress);
    }

    public void browse() {
        try (Connection connection = getConnection()) {
            Statement st = connection.createStatement();

            String query = "SELECT * FROM Item";

            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String author = rs.getString("author");
                String title = rs.getString("title");
                System.out.println(id + ": " + title + ", " + author + ", " + type);
            }

        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }

    public void addItem(String title, String author, String url, String isbn, String type, String comment) throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO item (title, author, url, isbn, type, description, is_read) VALUES (?, ?, ?, ?, ? ,?, ?)");
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, url);
            ps.setString(4, isbn);
            ps.setString(5, type);
            ps.setString(6, comment);
            ps.setBoolean(7, false);
            ps.execute();
        } catch (Throwable t) {
            System.out.println("Error >> " + t.getMessage());
        }
    }
}
