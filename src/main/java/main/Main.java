package main;


import Dao.ItemDao;
import Data.Database;
import Item.ItemController;
import Item.ItemHandler;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //jdbc:sqlite::resource:main.db points to the src/main/resources/ -folder
        Database db = new Database("jdbc:sqlite::resource:main.db");
        ItemDao dao = new ItemDao(db);
        Application app = new Application(new ItemHandler(db, new ItemController(dao)));
        app.run();
    }
}