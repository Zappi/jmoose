import Dao.CommentDao;
import Dao.ItemDao;
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
		Comment testComment = new Comment("comment", 1);
		db = new Database("jdbc:sqlite::resource:test.db");
		ItemDao itemDao = new ItemDao(db);
		commentDao = new CommentDao(db);
	}

	 @Test
    public void findAllForItemTest() throws SQLException, ClassNotFoundException {
        List<String> comments = commentDao.findAllByItem(6);
        assertEquals(1, comments.size());
    }
}