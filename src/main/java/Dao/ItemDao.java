package Dao;

import Data.Database;
import Interface.Dao;
import Item.Item;

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
    public Item findOne(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Item> findAll() throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Item");
        ResultSet rs = ps.executeQuery();

        List<Item> allItems = new ArrayList();

        while (rs.next()) {
            String title = rs.getString("title");
            String author = rs.getString("author");
            String url = rs.getString("url");
            String isbn = rs.getString("isbn");
            String type = rs.getString("type");
            String description = rs.getString("description");
            boolean is_read = rs.getBoolean("is_read");
            allItems.add(new Item(title, author, url, isbn, type, description, is_read));
        }

        rs.close();
        ps.close();
        connection.close();

        return allItems;
    }


    @Override
    public void Delete(String name) throws SQLException {

    }

    @Override
    public void save(String title, String author, String url, String isbn, String type, String description) throws ClassNotFoundException, SQLException {
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
    }
}