package main;


import Dao.ItemDao;
import Data.Database;
import Item.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Application {

    private ItemController itemController;
    public Scanner scanner;

    public Application(ItemController itemController) {
        this.scanner = new Scanner(System.in);
        this.itemController = itemController;
    }


    //UI not tested/tested manually
    public void run() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, would you like to save an item or browse your collection?");
        while (true) {
            System.out.println("[Save, Browse, Find One, Quit]");
            String request = scanner.nextLine().toLowerCase();

            request = request.toLowerCase();
            if (request.equals("save")) {
                System.out.println("");
                itemController.saveItem(scanner);
            } else if (request.equals("browse")) {
                itemController.browseItems(scanner);
            } else if(request.equals("find one")) {
                itemController.findOne(scanner);
            }   else if (request.equals("quit")) {
                System.out.println("Good bye!");
                break;
            } else {
                System.out.println("Command not recognized, try using one of the above mentioned commands! \n");
            }
        }
    }
}
