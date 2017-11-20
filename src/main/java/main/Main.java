package main;


import Data.Database;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        Database db = new Database("jdbc:sqlite:main.db");
        Application app = new Application(db);
        app.run();
    }
}