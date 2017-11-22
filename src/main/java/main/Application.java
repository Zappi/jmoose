package main;


import Dao.ItemDao;
import Data.Database;
import Item.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Application {

    private Database db;
    public Scanner scanner;
    public ItemDao itemDao;


    public Application(Database db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
        this.itemDao = new ItemDao(db);
    }


    //UI not tested/tested manually
    public void run() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, would you like to save an item or browse your collection?");
        while (true) {
            System.out.println("[Save, Browse, Quit]");
            String request = scanner.nextLine();
            request = request.toLowerCase();
            if (request.equals("save")) {
                System.out.println("");
                saveItem(db, scanner);
            } else if (request.equals("browse")) {
                browseItems(db);
            } else if (request.equals("quit")) {
                System.out.println("Good bye!");
                break;
            } else {
                System.out.println("Command not recognized, try using one of the above mentioned commands! \n");
            }
        }
    }

    //UI not tested/tested manually
    private void browseItems(Database db) throws SQLException, ClassNotFoundException {
        List<Item> allItems = itemDao.findAll();
        for (Item item : allItems) {
            System.out.println(item);
        }
    }

    public void saveItem(Database db, Scanner scanner) throws SQLException, ClassNotFoundException {
        System.out.println("Input the information of the item");
        System.out.println("Title: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title can not be null");
            return;
        }
        System.out.println("Author: ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            System.out.println("Author can not be empty, if the author is anonymous add that");
            return;
        }
        System.out.println("URL: ");
        String url = scanner.nextLine();
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println("Type: ");
        String type = scanner.nextLine();
        System.out.println("Description: ");
        String description = scanner.nextLine();
        itemDao.save(title, author, url, isbn, type, description);
        System.out.println("Item saved succesfully!");
    }
}
