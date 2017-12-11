package main;


import Comment.CommentHandler;
import Item.ItemHandler;


import java.sql.SQLException;
import java.util.Scanner;



public class Application {

    private ItemHandler itemHandler;
    private CommentHandler commentHandler;
    public Scanner scanner;
    private UI ui;

    public Application(ItemHandler itemHandler, CommentHandler commentHandler) {
        this.scanner = new Scanner(System.in);
        this.itemHandler = itemHandler;
        this.ui = new UI(this.itemHandler, this.scanner, commentHandler);
        this.commentHandler = commentHandler;
    }


    //UI not tested/tested manually
    public void run() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, would you like to save an item or browse your collection?");
        while (true) {
            System.out.println("[Enter or (B)rowse, (F)ilter, (S)ave, (G)et One, (Q)uit]");

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
