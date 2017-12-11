package Item;

import Dao.ItemDao;
import Data.Database;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ItemControllerTest {

    public ItemController itemController;
    private Database db;
    private ItemDao itemDao;
    private Item testItem;

    @Before
    public void setUp() {
        testItem = new Item(1, "title", "author", "URL", "isbn", "type", "description", false);
        db = new Database("jdbc:sqlite::resource:test.db");
        this.itemController = new ItemController(new ItemDao(db));
        this.itemDao = new ItemDao(db);
    }


    @Test
    public void browsingItemsWorks() throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> items = itemController.browseItems();
        assertTrue(items.size() > 0);
    }

    @Test
    public void urlIsModifiedCorrecetly() {
        String url = "www.google.com";
        String modifiedURL = itemController.handleUrl(url);
        assertEquals("http://www.google.com", modifiedURL);
    }

    @Test
    public void urlHasCorrectName() {
        String url = itemController.handleUrl("http://www.google.com");
        assertEquals("http://www.google.com", url);
        url = itemController.handleUrl("https://www.google.com");
        assertEquals("https://www.google.com", url);
    }

    @Test
    public void savingItemWorks() throws SQLException, ClassNotFoundException {
        itemController.save("title", "author", "URL", "isbn", "type", "description");
        Item i = itemController.getOneItemByTitle("title");
        itemController.deleteByTitle("title");
        assertEquals(i.getIsbn(), "isbn");
    }

    @Test
    public void changingReadStatusWorks() throws SQLException, ClassNotFoundException {
        itemController.changeReadStatus("r", "Da Vinci Code");
        Item i = itemController.getOneItemByTitle("Da Vinci Code");
        assertTrue(i.getIs_read());
        itemController.changeReadStatus("unread", i.getTitle());
    }

    @Test
    public void changingReadStatusToUnread() throws SQLException, ClassNotFoundException {
        itemController.changeReadStatus("unread", "Da Vinci Code");
        itemController.changeReadStatus("u", "Da Vinci Code");
        Item i = itemController.getOneItemByTitle("Da Vinci Code");
        assertFalse(i.getIs_read());
    }

    @Test
    public void getReadWorks() throws SQLException, ClassNotFoundException {
        itemController.changeReadStatus("read", "Da Vinci Code");
        Map<Integer, Item> newMap = new HashMap<>();
        newMap.put(1, testItem);
        Map<Integer, Item> items = itemController.getRead(newMap);
        assertTrue(items.size() > 0);
        itemController.changeReadStatus("unread", "Da Vinci Code");
    }

    @Test
    public void getUnReadWorks() throws SQLException, ClassNotFoundException {
        Map<Integer, Item> newMap = new HashMap<>();
        newMap.put(1, testItem);
        Map<Integer, Item> items = itemController.getUnread(newMap);
        assertTrue(items.size() > 0);
    }
}
