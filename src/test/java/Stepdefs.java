import Dao.ItemDao;
import Data.Database;
import Item.Item;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Stepdefs {

    private Database db;
    private ItemDao itemDao;
    private Item testItem;
    private List<Item> items;

    @Given("^Database is initialized$")
    public void initializingDatabase() throws SQLException, ClassNotFoundException {
        this.db = new Database("jdbc:sqlite::resource:test.db");
        this.itemDao = new ItemDao(db);
    }

    @When("^I request a listing of saved items$")
    public void userListingSavedItems() throws SQLException, ClassNotFoundException {
        this.items = itemDao.findAll();
    }

    @When("^Item is added$")
    public void addingNewItemToDatabase() throws SQLException, ClassNotFoundException {
        this.itemDao.save("The Alchemist", "Paulo Coelho", "", "1234-4556", "book", "International bestseller");
    }

    @Then("^items should be displayed$")
    public void displayingItems() throws SQLException, ClassNotFoundException {
        assertNotNull(items);
    }

    @Then("^Item should be stored in database$")
    public void findAddedItemFromDatabase() throws SQLException, ClassNotFoundException {
        assertEquals("The Alchemist", itemDao.findOneByAuthor("Paulo Coelho").getTitle());
    }

    @And("^item with text \"([^\"]*)\" should be displayed$")
    public void itemWithTextShouldBeDisplayed(String arg0) throws Throwable {
        List<Item> allItems = itemDao.findAll();
        boolean itemFound = false;
        for (Item item : allItems) {
            if (item.getTitle().equals(arg0)){
                itemFound = true;
            }
        }

        assertTrue(itemFound);
    }
}




