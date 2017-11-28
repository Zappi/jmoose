package Interface;

import java.sql.SQLException;
import java.util.List;

public interface Dao<O, K> {

    O findOne(K key) throws SQLException, ClassNotFoundException;
    List<O> findAll() throws SQLException, ClassNotFoundException;
    boolean delete(K key) throws SQLException, ClassNotFoundException;
    //boolean save(O object) throws SQLException, ClassNotFoundException;
}
