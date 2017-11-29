import Dao.CommentDao;
import Data.Database;
import Comment.Comment;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CommentDaoTest {

	private Database db;
	private CommentDao commentDao;
	private Comment comment;	

	@Before
	public void setUp() {
		testComment = new Comment("comment", "itemId");
		db = new Database("jdbc:sqlite::resource:test.db");
		itemDao = new itemDao(db);
	}

	 @Test
    public void findAllForItemTest() throws SQLException, ClassNotFoundException {
        List<String> comments = commentDao.findAllByItem(6);
        assertEquals(comments.size(), 2);
    }
}