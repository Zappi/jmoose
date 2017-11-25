package main;


import Item.ItemHandler;

import java.sql.SQLException;
import java.util.Scanner;


public class Application {

    private ItemHandler itemHandler;
    public Scanner scanner;

    public Application(ItemHandler itemHandler) {
        this.scanner = new Scanner(System.in);
        this.itemHandler = itemHandler;
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
                itemHandler.saveItem(scanner);
            } else if (request.equals("browse")) {
                itemHandler.getItems(scanner);
            } else if(request.equals("find one")) {
                itemHandler.findOne(scanner);
            }   else if (request.equals("quit")) {
                System.out.println("Good bye!");
                break;
            } else {
                System.out.println("Command not recognized, try using one of the above mentioned commands! \n");
            }
        }
    }
}
