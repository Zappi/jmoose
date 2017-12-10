import Controller.CommentController;
import Dao.CommentDao;
import Dao.ItemDao;
import Data.Database;
import Model.Item;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class Stepdefs {

    private Database db;
    private ItemDao itemDao;
    private Item testItem;
    private List<Item> items;
    private CommentDao commentDao;
    private CommentController commentController;



    @Given("^Database is initialized$")
    public void initializingDatabase() throws SQLException, ClassNotFoundException {
        this.db = new Database("jdbc:sqlite::resource:test.db");
        this.itemDao = new ItemDao(db);
        this.commentDao = new CommentDao(db);
        this.commentController = new CommentController(commentDao);

    }

    @When("^I request a listing of saved items$")
    public void userListingSavedItems() throws SQLException, ClassNotFoundException {
        this.items = itemDao.findAll();
    }

    @When("^Item is added$")
    public void addingNewItemToDatabase() throws SQLException, ClassNotFoundException {
        this.itemDao.save("The Alchemist", "Paulo Coelho", "", "1234-4556", "book", "International bestseller");
    }

    @When("^I add a comment to an item$")
    public void iAddACommentToAnItem() throws SQLException, ClassNotFoundException {
        Item i = itemDao.findOneByTitle("Da Vinci Code");
        commentDao.save("A great book", i.getId());
    }

    @Then("^items should be displayed$")
    public void displayingItems() throws SQLException, ClassNotFoundException {
        assertNotNull(items);
    }

    @Then("^Item should be stored in database$")
    public void findAddedItemFromDatabase() throws SQLException, ClassNotFoundException {
        assertEquals("The Alchemist", itemDao.findOneByAuthor("Paulo Coelho").getTitle());
    }

    @Then("^I can view it$")
    public void viewingASingleItem() throws SQLException, ClassNotFoundException {
        Item i = itemDao.findOneByTitle("Da Vinci Code");
        assertEquals(i.getAuthor(), "Brown Dan");
    }

    @Then("^Chosen item is marked read$")
    public void itemIsMarkedRead() throws SQLException, ClassNotFoundException {
        Item i = itemDao.findOneByTitle("Da Vinci Code");
        assertTrue(i.getIs_read());
        itemDao.changeRead(false, "Da Vinci Code");
    }

    @And("^I choose a single item$")
    public void choosingASingleItem() throws SQLException, ClassNotFoundException {
        Item i = itemDao.findOneByAuthor("Brown Dan");
        assertEquals("Da Vinci Code", i.getTitle());
    }

    @And("^item with text \"([^\"]*)\" should be displayed$")
    public void itemWithTextShouldBeDisplayed(String arg0) throws Throwable {
        List<Item> allItems = itemDao.findAll();
        boolean itemFound = false;
        for (Item item : allItems) {
            if (item.getTitle().equals(arg0)) {
                itemFound = true;
            }
        }

        assertTrue(itemFound);
    }

    @And("^I mark it read$")
    public void markItemRead() throws SQLException, ClassNotFoundException {
        itemDao.changeRead(true, "Da Vinci Code");
    }

    @Then("^Comment is added$")
    public void commentIsAdded() throws SQLException, ClassNotFoundException {
        Item i = itemDao.findOneByTitle("Da Vinci Code");
        List<String> comments = commentDao.findAllByItem(i.getId());
        assertTrue(comments.size() > 0);
        commentController.deleteAllFromOneItem(i.getId());
    }
}




