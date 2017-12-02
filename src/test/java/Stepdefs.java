import Dao.ItemDao;
import Data.Database;
import Item.Item;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @When("^I reguest a listing of saved items$")
    public void userListingSavedItems() throws SQLException, ClassNotFoundException {
        this.items = itemDao.findAll();
    }

    @When("^Item is added$")
    public void addingNewItemToDatabase() throws SQLException, ClassNotFoundException {
        this.itemDao.save("The Alchemist", "Paulo Coelho", "", "1234-4556", "book", "International bestseller");
    }

    @Then("^Items should be displayed$")
    public void displayingItems() throws SQLException, ClassNotFoundException {
        assertNotNull(items);
        assertEquals("Da Vinci Code", itemDao.findOne("1"));
    }

    @Then("^Item should be stored in database$")
    public void findAddedItemFromDatabase() throws SQLException, ClassNotFoundException {
        assertEquals("The Alchemist", itemDao.findOneByAuthor("Paulo Coelho").getTitle());
    }

}




