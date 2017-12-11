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

      //pr seur 2 riviä 
        this.ui = new UI(this.itemHandler, this.scanner);
        this.commentHandler = commentHandler;
    }


    //UI not tested/tested manually
    public void run() throws SQLException, ClassNotFoundException {
        System.out.println("Hello, would you like to save an item or browse your collection?");
        while (true) {
            System.out.println("[Enter or (B)rowse, (F)ilter, (S)ave, (G)et One, (Q)uit]");

            String request = scanner.nextLine().toLowerCase();

            request = request.toLowerCase();

   // pr muutoksia:
   //         if (request.equals("q") || request.equals("quit")) {
   //             break;
   //         } else {
   //             this.ui.doCommand(request);

            if (request.equals("s") || request.equals("save")) {
                System.out.println("");
                itemHandler.saveItem(scanner);
            } else if (request.isEmpty() || request.equals("b") || request.equals("browse")) {
                itemHandler.getItems(scanner);
            } else if (request.equals("f") || (request.equals("filter"))) {
                itemHandler.filterSearch(scanner);
            } else if (request.equals("g") || request.equals("get")) {
                itemHandler.findOne(scanner);
            } else if (request.equals("q") || request.equals("quit")) {
                System.out.println("Good bye!");
                break;
            } else if (request.matches("\\d+")) {
                if (itemHandler.getOne(Integer.parseInt(request)) != null)
                    System.out.println(itemHandler.getOne(Integer.parseInt(request)) + "\n");
            } else {
                System.out.println("Command not recognized, try using one of the above mentioned commands! (s, b, f, q)\n");
            }
        }
    }
}
