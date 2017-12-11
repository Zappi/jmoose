package Dao;

import Comment.Comment;
import Data.Database;
import Interface.Dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements Dao<Comment, Integer> {

    private Database database;

    public CommentDao(Database db) {
        this.database = db;
    }


    @Override
    public Comment findOne(Integer key) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Comment> findAll() throws SQLException, ClassNotFoundException {
        // Tähän toteutetaan kaikkien kommenttien listaus
        // Tätähän ei oikeastaan tarvita, koska ei ole käyttötapausta, jossa listattaisiin kaikki kommentit
        return null;
    }

    @Override
    public boolean delete(Integer key) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM Comment WHERE item= ? ");
        ps.setInt(1, key);
        ps.executeUpdate();
        ps.close();
        connection.close();
        return true;
    }

    //Palauttaa haetun Itemin kommentit listana String-olioita 
    public List<String> findAllByItem(int key) throws SQLException, ClassNotFoundException {
        Connection connection = database.getConnection();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Comment WHERE item = ?");
        ps.setInt(1, key);
        ResultSet rs = ps.executeQuery();

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
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Comment (comment, item) VALUES (?, ?)");

        ps.setString(1, comment);
        ps.setInt(2, itemId);

        ps.execute();

        ps.close();

        connection.close();

        return true;
    }
}
