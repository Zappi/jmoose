package main;


import Item.ItemHandler;


import java.sql.SQLException;
import java.util.Scanner;



public class Application {

    private ItemHandler itemHandler;
    public Scanner scanner;
    private UI ui;

    public Application(ItemHandler itemHandler) {
        this.scanner = new Scanner(System.in);
        this.itemHandler = itemHandler;
        this.ui = new UI(this.itemHandler, this.scanner);
    }


    //UI not tested/tested manually
    public void run() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, would you like to save an item or browse your collection?");
        while (true) {
            System.out.println("[(S)ave, (B)rowse( or press Enter), (F)ind One, (A)dd comment, (Q)uit]");

            String request = scanner.nextLine().toLowerCase();

            request = request.toLowerCase();
            if (request.equals("q") || request.equals("quit")) {
                break;
            } else {
                this.ui.doCommand(request);
            }
        }
    }
}
