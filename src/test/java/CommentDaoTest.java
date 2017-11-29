import Dao.CommentDao;
import Dao.ItemDao;
import Data.Database;
import Comment.Comment;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CommentDaoTest {

	private Database db;
	private CommentDao commentDao;
	private Comment comment;


	@Before
	public void setUp() {
		db = new Database("jdbc:sqlite::resource:test.db");
<<<<<<< HEAD
		ItemDao itemDao = new ItemDao(db);
=======
		Comment testComment = new Comment("comment", 1);
>>>>>>> cf8fa21eab2464d8ad1eeb0f223b7a4bd808b5f8
		commentDao = new CommentDao(db);
	}

	@Test
    public void findAllForItemTest() throws SQLException, ClassNotFoundException {
<<<<<<< HEAD
        List<String> comments = commentDao.findAllByItem(6);
        assertEquals(1, comments.size());
=======
        List<String> comments = commentDao.findAllByItem(8);
		assertEquals(0, comments.size());
>>>>>>> cf8fa21eab2464d8ad1eeb0f223b7a4bd808b5f8
    }
}