package Item;

import Comment.CommentController;
import Dao.CommentDao;
import Data.Database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ItemHandler {

    private ItemController itemController;
    private CommentController commentController;

    public ItemHandler(Database db, ItemController itemController, CommentController commentController) {
        this.itemController = itemController;
        this.commentController = commentController;
    }

    public void getItems(Scanner scanner) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> itemMap = itemController.browseItems();
        int i = 1;
        for (Item item : itemMap.values()) {
            System.out.println(i + " " + item);
            i++;
        }
        selectSingleItemFromTheList(scanner, itemMap);
    }

    private void selectSingleItemFromTheList(Scanner scanner, HashMap listedItems) throws SQLException, ClassNotFoundException {
        System.out.println("Would you like to see the single item info? [Yes or no]");
        boolean answer = false;
        while (!answer) {
            String answerString = scanner.nextLine().toLowerCase();

            if(answerString.toLowerCase().equals("no") || answerString.toLowerCase().equals("n")) {
                return;
            } else if (answerString.toLowerCase().equals("yes") || answerString.toLowerCase().equals("y")) {
                answer = true;
            } else {
                System.out.println("Command not recognized try [Yes or no]");
            }
        }

        findOne(scanner);

    }
    public void saveItem(Scanner scanner) throws SQLException, ClassNotFoundException {
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

        System.out.println("Type: ");
        String type = scanner.nextLine();
        if (type.isEmpty()) {
            System.out.println("Type can not be empty");
            return;
        }

        String url;
        if (!type.equals("book")) {
            System.out.println("URL: ");
            url = scanner.nextLine();
        } else {
            url = null;
        }

        String isbn;
        if (type.equals("book")) {
            System.out.println("ISBN: ");
            isbn = scanner.nextLine();
        } else {
            isbn = null;
        }

        System.out.println("Description: ");
        String description = scanner.nextLine();
        itemController.save(title, author, url, isbn, type, description);
        System.out.println("Item saved successfully!");
    }



    public void findOne(Scanner scanner) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> listedItems = itemController.browseItems();
        System.out.println("Which number?");
        int index = Integer.parseInt(scanner.nextLine());
        Item wantedItem = (Item) listedItems.get(index);
        System.out.println(wantedItem);

        //Tämä tulostaa myös yhden itemin kommentit aikanaan
        //List<String> comments = commentController.listComments(wantedItem.getId());
        //if (comments != null){
        //    for (int i = 0; i < comments.size(); i++) {
        //        System.out.println(comments.get(i));
        //    }
        //}

        if(wantedItem.getUrl() != null)  {
            System.out.println("Would you like to open item's link in your browser? [Yes or no]");
            if(scanner.nextLine().toLowerCase().equals("yes") || scanner.nextLine().toLowerCase().equals("y")) {
                itemController.openItemLink(wantedItem.getUrl());
            }
        }
    }

    public void addComment(Scanner scanner) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> itemMap = itemController.browseItems();
        int i = 1;
        for (Item item : itemMap.values()) {
            System.out.println(i + " " + item);
            i++;
        }
        System.out.println("Which item would you like to add comment to? ");
        int number = Integer.parseInt(scanner.nextLine());
        int item = itemMap.get(number).getId();
        System.out.println("Comment: ");
        String comment = scanner.nextLine();
        commentController.save(comment, item);
        System.out.println("Comment saved succesfully!");
    }
}
