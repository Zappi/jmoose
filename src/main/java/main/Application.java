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
            System.out.println("[(S)ave, (B)rowse( or press Enter), (F)ind One, (A)dd comment, (Q)uit]");

            String request = scanner.nextLine().toLowerCase();

            request = request.toLowerCase();
            if (request.contains("save") || request.equals("s")) {
                System.out.println("");
                itemHandler.saveItem(scanner);
            } else if (request.equals("browse") || request.isEmpty() || request.equals("b")) {
                itemHandler.getItems(scanner);
            } else if (request.equals("f")) {
                itemHandler.findOne(scanner);
            } else if (request.equals("q") || request.equals("quit")) {
                break;
            } else if(request.equals("add comment") || request.equals("a")) {
                itemHandler.addComment(scanner);
            } else if (request.equals("quit") || request.equals("q")) {
                System.out.println("Good bye!");
                break;
            } else if (request.matches("\\d+")){
                if (itemHandler.getOne(Integer.parseInt(request)) != null)
                    System.out.println(itemHandler.getOne(Integer.parseInt(request)) + "\n");
            } else {
                System.out.println("Command not recognized, try using one of the above mentioned commands! \n");
            }
        }
    }
}
