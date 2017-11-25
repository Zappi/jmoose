import Dao.ItemDao;
import Data.Database;
import Item.ItemController;
import org.junit.Before;
import org.junit.Test;
import Item.Item;

import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ItemControllerTest {

    private ItemController itemController;
    private Database db;
    private Item item;

    @Before
    public void setUp() {
        db = new Database("jdbc:sqlite::resource:test.db");
        this.itemController = new ItemController(new ItemDao(db));
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
}
