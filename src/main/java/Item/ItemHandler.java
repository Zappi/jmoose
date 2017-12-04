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
            System.out.println(i + " " + item.printForBrowse());
            i++;
        }
        findOne(scanner);
    }

/*    private void selectSingleItemFromTheList(Scanner scanner, HashMap listedItems) throws SQLException, ClassNotFoundException {
        System.out.println("Would you like to see the single item info? [Yes or no]");
        boolean answer = false;
        while (!answer) {
            String answerString = scanner.nextLine().toLowerCase();

            if (answerString.toLowerCase().equals("no") || answerString.toLowerCase().equals("n")) {
                return;
            } else if (answerString.toLowerCase().equals("yes") || answerString.toLowerCase().equals("y")) {
                answer = true;
            } else {
                System.out.println("Command not recognized try [Yes or no]");
            }
        }

        findOne(scanner);

    }*/

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
        System.out.println("Enter digit to find by ID. Enter or text returns");
        String input = scanner.nextLine();
        if (!input.matches("\\d+")){
            return;
        }
        int index = Integer.parseInt(input);
        Item wantedItem = getOne(index);

        System.out.println(wantedItem + "\n");
        if (wantedItem == null) {
            System.out.println("Invalid item! Please try again.\n");
            return;
        }

        //Tämä tulostaa myös yhden itemin kommentit aikanaan
        //List<String> comments = commentController.listComments(wantedItem.getId());
        //if (comments != null){
        //    for (int i = 0; i < comments.size(); i++) {
        //        System.out.println(comments.get(i));
        //    }
        //}

        markReadStatus(wantedItem, scanner);
        
        if (wantedItem.getUrl() != null) {
            openUrl(wantedItem, scanner);
        }
    }

    private void changeReadStatus(String command, String title) throws SQLException, ClassNotFoundException {
        itemController.changeReadStatus(command, title);
    }

    private void openSingleItemLink(Item wantedItem) {
        itemController.openItemLink(wantedItem.getUrl());
    }

    private void markReadStatus(Item wantedItem, Scanner scanner) throws SQLException, ClassNotFoundException {
        System.out.println("Mark item as read/unread? [Read or Unread]");
        String command = scanner.nextLine().toLowerCase();
        if (command.equals("r") || command.equals("u") || command.equals("read") || command.equals("unread")) {
            changeReadStatus(command, wantedItem.getTitle());
        }
    }

    private void openUrl(Item wantedItem, Scanner scanner) throws SQLException, ClassNotFoundException{
        if (!wantedItem.getUrl().isEmpty()) {
            System.out.println("Would you like to open item's link in your browser? [Yes or no]");
            String command = scanner.nextLine();
            if (command.equals("r") || command.equals("u") || command.equals("read") || command.equals("unread")) {
                changeReadStatus(command, wantedItem.getTitle());
                return;
            } else if (command.equals("yes") || command.equals("y")) {
                openSingleItemLink(wantedItem);
                return;
            }
        }
    }

    public Item getOne(int itemId) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> listedItems = itemController.browseItems();
        Item wantedItem = listedItems.get(itemId);
        return wantedItem;
    }
}
