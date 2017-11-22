package Interface;

import java.sql.SQLException;

import Item.Item;

import java.util.List;

public interface Dao<Item, String> {

    Item findOne(String title) throws SQLException, ClassNotFoundException;
    List<Item> findAll() throws SQLException, ClassNotFoundException;
    boolean delete(String title) throws SQLException, ClassNotFoundException;
    boolean save(String title, String author, String url, String isbn, String type, String description) throws SQLException, ClassNotFoundException;
}
