
import Data.Database;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {
    
    private Database db;
    
    @Before
    public void setUp() {
        db = new Database("jdbc:sqlite:test.db");
    }
    
    @Test
    public void addingItems() throws SQLException, ClassNotFoundException {
        db.addItem("Book", "author", "", "", "", "");
        String title = db.getItem("Book");
        assertEquals(title, "Book");
        db.deleteByTitle("Book");
    }
}
