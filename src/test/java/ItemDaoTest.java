import Dao.ItemDao;
import Data.Database;
import org.junit.Before;
import org.junit.Test;
import Item.Item;
import java.sql.SQLException;
import java.util.List;
import static junit.framework.Assert.*;

public class ItemDaoTest {

    private Database db;
    private ItemDao itemDao;
    private Item testItem;

    @Before
    public void setUp() {
        testItem = new Item("title", "author", "URL", "isbn", "type", "description", false);
        db = new Database("jdbc:sqlite::resource:test.db");
        itemDao = new ItemDao(db);
    }

    @Test
    public void findAllTest() throws SQLException, ClassNotFoundException {
        List<Item> items = itemDao.findAll();
        assertEquals(items.get(3).getTitle(), "A Man Called Ove");
    }

    @Test
    public void deletingItems() throws SQLException, ClassNotFoundException {
        itemDao.save("title", "author", "URL", "isbn", "type", "description");
        assertTrue(itemDao.delete("title"));
    }

    @Test
    public void findOneTest() throws SQLException, ClassNotFoundException {
        Item found = itemDao.findOne("Da Vinci Code");
        assertEquals(found.getAuthor(), "Brown Dan");
    }
}