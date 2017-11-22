package main;


import Data.Database;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //jdbc:sqlite::resource:main.db points to the src/main/resources/ -folder
        Database db = new Database("jdbc:sqlite::resource:main.db");
        Application app = new Application(db);
        app.run();
    }
}