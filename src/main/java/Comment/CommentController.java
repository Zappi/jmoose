package Comment;

import Dao.CommentDao;


import java.sql.SQLException;
import java.util.List;


public class CommentController {
	private CommentDao commentDao;

	public CommentController(CommentDao commentDao) {
		this.commentDao = commentDao;		
	}

	public List<String> listComments(int itemId) throws SQLException, ClassNotFoundException {
		return this.commentDao.findAllByItem(itemId);
	}

	public void save(String comment, int itemId) throws SQLException, ClassNotFoundException {
		commentDao.save(comment, itemId);
	}

	public void deleteAllFromOneItem(int itemId) throws SQLException, ClassNotFoundException {
		commentDao.delete(itemId);
	}
}
