package Comment;

import Item.Item;
import Item.ItemController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
        System.out.println("Comment saved successfully!");
    }

    public void addCommentForBrowsedItem(Scanner scanner, Item item) throws SQLException, ClassNotFoundException {
        int itemId = itemController.getOneItemByTitle(item.getTitle()).getId();
        if(!doesUserWanToAddNewComment(scanner)) {
            return;
        }

        System.out.println("Type in the comment: (use enter for a line break and empty line to quit)");
        String comment = "";

        while (true) {
            String text = scanner.nextLine();
            if (text.equals("")) {
                break;
            } else {
                comment += "\n" + text;
            }
        }
        if (!comment.equals("")){

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            String timestamp = dtf.format(now);
            comment = timestamp + ":" + comment + "\n";


            commentController.save(comment, itemId);
            System.out.println("Comment saved succesfully!");
        } else {
            System.out.println("No comment saved.");
        }
    }

    private boolean doesUserWanToAddNewComment(Scanner scanner) {
        System.out.println("Would you like to add a new comment for the item? (y/n)");
        String response = scanner.nextLine();
        if(response.contains("y")) {
            return true;
        }
        return false;
    }

    public void printComments(Item item) throws SQLException, ClassNotFoundException {
        List<String> comments = commentController.listComments(item.getId());
        System.out.println("Comments: ");
        if (comments.size() == 0) {
            System.out.print("No comments yet, add the first one!\n\n");
        } else if (comments != null){
            for (int i = 0; i < comments.size(); i++) {
                System.out.println(comments.get(i));
            }
            System.out.println();
        } else return;
    }
}
