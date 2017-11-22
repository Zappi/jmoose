package Interface;

import java.sql.SQLException;
import Item.Item;
import java.util.List;

public interface Dao<Item, String>{

    Item findOne(String title) throws SQLException, ClassNotFoundException;
    List<Item> findAll() throws SQLException, ClassNotFoundException;
    void Delete(String title) throws SQLException, ClassNotFoundException;
    void save(String title, String author, String url, String isbn, String type, String description) throws SQLException, ClassNotFoundException;
}
