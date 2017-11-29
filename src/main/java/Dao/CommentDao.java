package Dao;

import Data.Database;
import Comment.Comment;
import Interface.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements Dao<Comment, String>{

    private Database database;

    public CommentDao(Database db) {
        this.database = db;
    }


    @Override
    public List<Comment> findAll() throws SQLException, ClassNotFoundException {
        // Tähän toteutetaan kaikkien kommenttien listaus
        // Tätähän ei oikeastaan tarvita, koska ei ole käyttötapausta, jossa listattaisiin kaikki kommentit
        return null;
    }

    //Palauttaa haetun Itemin kommentit listana String-olioita 
    public List<String> findAllByItem(int key) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Comment WHERE item ='" + key + "'");

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return null;
        }

        List<String> comments = new ArrayList<>();

        while (rs.next()) {
            String comment = rs.getString("comment");
            comments.add(comment);
        }

        rs.close();
        ps.close();
        connection.close();

        return comments;
    }

    public boolean save(String comment, int itemId) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Comment (comment, id) VALUES (?, ?)");
        
        ps.setString(1, comment);
        ps.setInt(2, itemId);

        ps.execute();
        ps.close();
        connection.close();

        return true;
    }

    @Override
    public boolean delete(String key) throws SQLException, ClassNotFoundException {
        // Tätä ei toteuteta
        return false;
    }

    @Override
    public Comment findOne(String key) throws SQLException, ClassNotFoundException {
        // Tätä ei toteuteta
        return null;
    }
}