package main;


import Controller.CommentController;
import Handler.CommentHandler;
import Dao.CommentDao;
import Dao.ItemDao;
import Data.Database;
import Controller.ItemController;
import Handler.ItemHandler;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //jdbc:sqlite::resource:main.db points to the src/main/resources/ -folder
        Database db = new Database("jdbc:sqlite::resource:main.db");
        ItemDao dao = new ItemDao(db);
        CommentDao cDao = new CommentDao(db);
        ItemController ic = new ItemController(dao);
        CommentController cc = new CommentController(cDao);
        CommentHandler ch = new CommentHandler(ic, cc);
        Application app = new Application(new ItemHandler(ic,cc, ch),ch);
        app.run();
    }
}