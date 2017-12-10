package Dao;

import Data.Database;
import Interface.Dao;
import Model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDao implements Dao<Item, String> {

    private Database database;

    public ItemDao(Database db) {
        this.database = db;
    }

    @Override
    public Item findOne(String key) throws SQLException, ClassNotFoundException {

        Connection connection = database.getConnection();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Item WHERE id ='" + key + "'");

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }

        int id = rs.getInt("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        String url = rs.getString("url");
        String isbn = rs.getString("isbn");
        String type = rs.getString("type");
        String description = rs.getString("description");
        boolean is_read = rs.getBoolean("is_read");

        rs.close();
        ps.close();
        connection.close();

        return new Item(id, title, author, url, isbn, type, description, is_read);
    }

    public Item findOneByTitle(String title) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Item WHERE title = ?");
        ps.setString(1, title);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }

        int id = rs.getInt("id");
        title = rs.getString("title");
        String author = rs.getString("author");
        String url = rs.getString("url");
        String isbn = rs.getString("isbn");
        String type = rs.getString("type");
        String description = rs.getString("description");
        boolean is_read = rs.getBoolean("is_read");

        rs.close();
        ps.close();
        connection.close();

        return new Item(id, title, author, url, isbn, type, description, is_read);
    }

    public Item findOneByAuthor(String author) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Item WHERE author = ?");
        ps.setString(1, author);

        ResultSet rs = ps.executeQuery();

        int id = rs.getInt("id");
        String title = rs.getString("title");
        author = rs.getString("author");
        String url = rs.getString("url");
        String isbn = rs.getString("isbn");
        String type = rs.getString("type");
        String description = rs.getString("description");
        boolean is_read = rs.getBoolean("is_read");

        rs.close();
        ps.close();
        connection.close();

        return new Item(id, title, author, url, isbn, type, description, is_read);
    }


    @Override
    public List<Item> findAll() throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Item");
        ResultSet rs = ps.executeQuery();

        List<Item> allItems = new ArrayList();

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String url = rs.getString("url");
            String isbn = rs.getString("isbn");
            String type = rs.getString("type");
            String description = rs.getString("description");
            boolean is_read = rs.getBoolean("is_read");
            allItems.add(new Item(id, title, author, url, isbn, type, description, is_read));
        }

        rs.close();
        ps.close();
        connection.close();

        return allItems;
    }


    @Override
    public boolean delete(String title) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM Item WHERE title= ? ");
        ps.setString(1, title);
        ps.executeUpdate();
        ps.close();
        connection.close();
        return true;
    }

    //@Override
    //public boolean save(Item object) throws SQLException, ClassNotFoundException {
    //Tähän toteutetaan uusi oikea tallennusmuoto tietokantaan
    //    return false;
    //}

    public boolean save(String title, String author, String url, String isbn, String type, String description) throws ClassNotFoundException, SQLException {
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO item (title, author, url, isbn, type, description, is_read) VALUES (?, ?, ?, ?, ? ,?, ?)");
        ps.setString(1, title);
        ps.setString(2, author);
        ps.setString(3, url);
        ps.setString(4, isbn);
        ps.setString(5, type);
        ps.setString(6, description);
        ps.setBoolean(7, false);
        ps.execute();

        ps.close();
        connection.close();

        return true;
    }

    public boolean changeRead(boolean read, String title) throws SQLException, ClassNotFoundException {
        int readInt = 0;
        if (read) {
            readInt = 1;
        }
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE Item SET is_read = ? WHERE title = ?");
        ps.setInt(1, readInt);
        ps.setString(2, title);
        ps.execute();
        ps.close();
        connection.close();
        return true;
    }
}
