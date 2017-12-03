import Item.Item;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class ItemTest {


    private Item testItem;

    @Before
    public void setUp() {
        this.testItem = new Item(1,"title","author","URL","isbn","type","description",false);
    }

    @Test
    public void toStringTest() {
        assertEquals(testItem.toString(), "title, author, URL, type");
    }


    @Test
    public void getIdTest(){ assertEquals(testItem.getId(), 1);}

    @Test
    public void setIdTest(){
        testItem.setId(2);
        assertEquals(testItem.getId(), 2);
    }

    @Test
    public void setTitleTest() {
        testItem.setTitle("test");
        assertEquals(testItem.getTitle(), "test");
    }

    @Test
    public void getTitleTest() {
        assertEquals(testItem.getTitle(), "title");
    }

    @Test
    public void getAuthorTest() {
        assertEquals(testItem.getAuthor(), "author");
    }

    @Test
    public void setAuthorTest() {
        testItem.setAuthor("test");
        assertEquals(testItem.getAuthor(), "test");
    }

    @Test
    public void getUrlTest() {
        assertEquals(testItem.getUrl(), "URL");
    }

    @Test
    public void setUrlTest() {
        testItem.setUrl("test");
        assertEquals(testItem.getUrl(), "test");
    }

    @Test
    public void getTypeTest() {
        assertEquals(testItem.getType(), "type");
    }

    @Test
    public void setTypeTest() {
        testItem.setType("test");
        assertEquals(testItem.getType(), "test");
    }

    @Test
    public void getIsbnTest() {
        assertEquals(testItem.getIsbn(), "isbn");
    }

    @Test
    public void setIsbnTest() {
        testItem.setIsbn("test");
        assertEquals(testItem.getIsbn(), "test");
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(testItem.getDescription(), "description");
    }

    @Test
    public void setDescriptionTest() {
        testItem.setDescription("test");
        assertEquals(testItem.getDescription(), "test");
    }

    @Test
    public void getIsReadTest() {
        assertFalse(testItem.getIs_read());
    }

    @Test
    public void setIsReadTest() {
        testItem.setIs_read(true);
        assertTrue(testItem.getIs_read());
    }
}
