package Dao;

import Data.Database;
import Model.Comment;
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
		db = new Database("jdbc:sqlite::resource:test.db");
		ItemDao itemDao = new ItemDao(db);
		Comment testComment = new Comment("comment", 1);
		commentDao = new CommentDao(db);
	}

	@Test
    public void findAllForItemTest() throws SQLException, ClassNotFoundException {
        List<String> comments = commentDao.findAllByItem(6);
        assertEquals(1, comments.size());
        comments = commentDao.findAllByItem(8);
		assertEquals(0, comments.size());
    }

    @Test
	public void saveTest() throws SQLException, ClassNotFoundException {
		commentDao.save("Kiva", 2);
		assertTrue(commentDao.findAllByItem(2).size() > 0);
		assertTrue(commentDao.delete(2));
	}

	@Test
    public void findOneNullTest() throws SQLException, ClassNotFoundException {
	    assertTrue(commentDao.findOne(1) == null);
    }

    @Test
    public void findAllNullTest() throws SQLException, ClassNotFoundException {
        assertTrue(commentDao.findAll() == null);
    }


}