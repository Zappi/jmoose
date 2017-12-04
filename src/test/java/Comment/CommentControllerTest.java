package Comment;

import Dao.CommentDao;
import Data.Database;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CommentControllerTest {
    public CommentController commentController;
    private Database db;
    private CommentDao commentDao;
    private Comment testComment;

    @Before
    public void setUp() {
        testComment = new Comment("kannattaa lukea",2);
        db = new Database("jdbc:sqlite::resource:test.db");
        this.commentDao = new CommentDao(db);
        this.commentController = new CommentController(commentDao);
    }

    @Test
    public void browsingCommentsByIdWorks()throws SQLException, ClassNotFoundException{
        List<String> c = commentController.listComments(1);
        assertTrue(c.size()>0);
    }

    @Test
    public void savingCommentsWorks() throws SQLException, ClassNotFoundException{
        commentController.save(testComment.getComment(), testComment.getItemId());
        List<String> c = commentController.listComments(2);
        commentController.deleteAllFromOneItem("2");
        assertTrue(c.size()>0);
    }
}
