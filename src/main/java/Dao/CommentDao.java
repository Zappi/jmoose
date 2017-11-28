package Dao;

import Data.Database;
import Comment.Comment;
import Interface.Dao;
import Item.Item;

import java.sql.SQLException;
import java.util.List;

public class CommentDao implements Dao<Comment, String>{

    private Database database;

    public CommentDao(Database db) {
        this.database = db;
    }


    @Override
    public List<Comment> findAll() throws SQLException, ClassNotFoundException {
        // Tähän toteutetaan kaikkien kommenttien listaus
        return null;
    }

    public List<Comment> findAllByItem(Item item) throws SQLException, ClassNotFoundException {
        // Tähän toteutetaan yhteen itemiin liittyvien kommenttien listaus
        return null;
    }

    @Override
    public boolean save(Comment comment) throws SQLException, ClassNotFoundException {
        // Tähän toteutetaan yhden kommentin tallennus tietokantaan
        return false;
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
