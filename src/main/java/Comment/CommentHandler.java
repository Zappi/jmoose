package Comment;

import Item.Item;
import Item.ItemController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class CommentHandler {

    private ItemController itemController;
    private CommentController commentController;


    public CommentHandler(ItemController ic, CommentController cc) {
        this.itemController = ic;
        this.commentController = cc;
    }

    public void addComment(Scanner scanner) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> itemMap = itemController.browseItems();
        int i = 1;
        for (Item item : itemMap.values()) {
            System.out.println(i + " " + item);
            i++;
        }
        int number;
        System.out.println("Which item would you like to add comment to? ");
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid item id");
            System.out.println();
            return;
        }

        int item = itemMap.get(number).getId();
        System.out.println("Comment: ");
        String comment = scanner.nextLine();
        commentController.save(comment, item);
        System.out.println("Comment saved succesfully!");
    }
}
