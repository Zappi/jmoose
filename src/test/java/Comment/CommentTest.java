package Comment;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentTest {
    private Comment testComment;

    @Before
    public void setUp() {
        this.testComment = new Comment("mukavaa luettavaa", 1);
    }

    @Test
    public void toStringTest(){
        assertEquals(testComment.toString(), "mukavaa luettavaa");
    }

    @Test
    public void getCommentTest(){
        assertEquals(testComment.getComment(), "mukavaa luettavaa");
    }

    @Test
    public void setCommentTest(){
        testComment.setComment("hyvä");
        assertEquals(testComment.getComment(), "hyvä");
    }

    @Test
    public void getItemIdTest() {assertEquals(testComment.getItemId(), 1);}

    @Test
    public void setItemIdTest() {
        testComment.setItemId(2);
        assertEquals(testComment.getItemId(), 2);
    }
}
