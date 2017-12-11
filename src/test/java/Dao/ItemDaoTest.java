package Dao;

import Dao.ItemDao;
import Data.Database;
import Item.Item;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
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
    public void findOneNullTest() throws SQLException, ClassNotFoundException {
        Item notFound = itemDao.findOne("4567");
        assertTrue(notFound == null);
    }

    @Test
    public void findOneByTitleTest() throws SQLException, ClassNotFoundException {
        Item found = itemDao.findOneByTitle("Da Vinci Code");
        assertEquals(found.getAuthor(), "Brown Dan");
    }

    @Test
    public void findOneByTitleNullTest() throws SQLException, ClassNotFoundException{
        Item notFound = itemDao.findOneByTitle("eiole");
        assertTrue(notFound == null);
    }

    @Test
    public void findOneByAUthorTest() throws SQLException, ClassNotFoundException {
        Item found = itemDao.findOneByAuthor("Brown Dan");
        assertEquals(found.getAuthor(), "Brown Dan");
    }
    @Test
    public void changingReadStatusTest() throws SQLException, ClassNotFoundException {
        Item i = itemDao.findOneByAuthor("Brown Dan");
        itemDao.changeRead(true ,i.getTitle());
        i = itemDao.findOneByAuthor("Brown Dan");
        assertTrue(i.getIs_read());
        itemDao.changeRead(false, i.getTitle());
    }

    @Test
    public void findingReadTest() throws SQLException,ClassNotFoundException {
        itemDao.changeRead(true,"Da Vinci Code");
        List<Item> items = itemDao.getRead();
        assertTrue(items.get(0).getIs_read());
        itemDao.changeRead(false, "Da Vinci Code");
    }
    @Test
    public void findingUnReadTest() throws SQLException, ClassNotFoundException {
        List<Item> items = itemDao.getUnread();
        assertFalse(items.get(0).getIs_read());
    }

    
    @Test
    public void deleteFailTest() throws ClassNotFoundException, SQLException {
        assertFalse(itemDao.delete(""));
    }
    
}
