import Dao.ItemDao;
import Data.Database;
import Item.Item;
import Item.ItemController;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ItemControllerTest {

    public ItemController itemController;
    private Database db;
    private ItemDao itemDao;
    private Item testItem;

    @Before
    public void setUp() {
        testItem = new Item(1,"title", "author", "URL", "isbn", "type", "description", false);
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
        assertEquals("http://www.google.com",modifiedURL);
    }

    @Test
    public void urlHasCorrectName() {
        String url = itemController.handleUrl("http://www.google.com");
        assertEquals("http://www.google.com", url);
    }

    @Test
    public void savingItemWorks() throws SQLException, ClassNotFoundException {
        itemController.save("title", "author", "URL", "isbn", "type", "description");
        Item i = itemController.getOneItemByTitle("title");
        itemController.deleteByTitle("title");
        assertEquals(i.getIsbn(), "isbn");
    }
}
