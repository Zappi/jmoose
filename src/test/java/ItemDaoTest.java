import Dao.ItemDao;
import Data.Database;
import Item.Item;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ItemDaoTest {

    private Database db;
    private ItemDao itemDao;
    private Item testItem;

    @Before
    public void setUp() {
        testItem = new Item(1,"title", "author", "URL", "isbn", "type", "description", false);
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
        Item found = itemDao.findOne("0");
        assertEquals(found.getTitle(), "Da Vinci Code");
    }

    @Test
    public void findOneByTitleTest() throws SQLException, ClassNotFoundException {
        Item found = itemDao.findOneByTitle("Da Vinci Code");
        assertEquals(found.getAuthor(), "Brown Dan");
    }

    @Test
    public void findOneByAUthorTest() throws SQLException, ClassNotFoundException {
        Item found = itemDao.findOneByAuthor("Brown Dan");
        assertEquals(found.getAuthor(), "Brown Dan");
    }
}