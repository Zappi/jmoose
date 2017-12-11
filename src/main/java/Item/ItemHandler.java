package Item;

import Comment.CommentController;
import Comment.CommentHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ItemHandler {

    private ItemController itemController;
    private CommentController commentController;
    private CommentHandler commentHandler;

    private final static int AUTHOR_FIELD_SIZE = 30;
    private final static int TYPE_FIELD_SIZE = 10;
    private final static int TITLE_FIELD_SIZE = 40;

    public ItemHandler(ItemController itemController, CommentController commentController, CommentHandler commentHandler) {
        this.itemController = itemController;
        this.commentController = commentController;
        this.commentHandler = commentHandler;
    }

    public void getItems(Scanner scanner) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> itemMap = itemController.browseItems();
        String printout = formatForBrowse(itemMap);
        System.out.println(printout);
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
        String type = scanner.nextLine().toLowerCase();
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

        if (wantedItem == null) {
            System.out.println("Invalid item! Please try again.\n");
            return;
        }

        System.out.println(wantedItem + "\n");


        commentHandler.printComments(wantedItem);
        markReadStatus(wantedItem, scanner);
        commentHandler.addCommentForBrowsedItem(scanner, wantedItem);
        
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
        System.out.println("Mark item as read/unread or press Enter to continue [(R)ead, (U)nread]");
        String command = scanner.nextLine().toLowerCase();
        if (command.equals("r") || command.equals("u") || command.equals("read") || command.equals("unread")) {
            changeReadStatus(command, wantedItem.getTitle());
        }
    }

    private void openUrl(Item wantedItem, Scanner scanner) throws SQLException, ClassNotFoundException{
        if (!wantedItem.getUrl().isEmpty()) {
            System.out.println("Would you like to open item's link in your browser? [(Y)es or (N)o]");
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


    public Item getOne(int index) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Item> listedItems = itemController.browseItems();
        Item wantedItem = listedItems.get(index);
        return wantedItem;
    }

    private String formatForBrowse(Map<Integer, Item> items) {
        System.out.println(headers());
        System.out.println(divider(headers().length()));
        int i = 1;
        String print = "";
        for (Item item : items.values()) {
            print += i + "\t|"
                    + formatTitle(item) + "|"
                    + formatAuthor(item) + "|"
                    + formatType(item) + "|"
                    + "\n";
            i++;
        }

        return print;
    }

    //UGLY! watch with care
    private String headers(){
        String hdrs = "ID\t|"
                +"Title";
        for (int i = 0; i < TITLE_FIELD_SIZE - "Title".length(); i++)
            hdrs += " ";

        hdrs += "|Author";

        for (int i = 0; i <AUTHOR_FIELD_SIZE - "Author".length(); i++)
            hdrs += " ";

        hdrs += "|Type";

        for (int i = 0; i < TYPE_FIELD_SIZE - "Type".length(); i++)
            hdrs += " ";

        hdrs += "|";
        return hdrs;
    }

    private String divider(int length){
        String div = "";
        for (int i = 0; i <= length; i++)
            div += "-";
        return div;
    }

    private String formatTitle(Item item) {
        String ret = "";
        ret += cullColumn(item.getTitle(), TITLE_FIELD_SIZE);
        for (int i = 0;i < TITLE_FIELD_SIZE - item.getTitle().length(); i++)
            ret += " ";
        return ret;
    }

    private String formatAuthor(Item item){
        String ret = "";
        ret += cullColumn(item.getAuthor(), AUTHOR_FIELD_SIZE);
        for (int i = 0;i < (AUTHOR_FIELD_SIZE - item.getAuthor().length()); i++)
            ret += " ";
        return ret;
    }

    private String formatType(Item item) {
        String ret = "";
        ret += cullColumn(item.getType(), TYPE_FIELD_SIZE);
        for (int i = 0; i < TYPE_FIELD_SIZE - item.getType().length(); i++)
            ret += " ";
        return ret;
    }

    private static String cullColumn(String content, int maxLength) {
        String ret = "";
        if (content.length() > maxLength) {
            ret += content.substring(0, maxLength - 3);
            ret += "...";
        } else {
            ret = content;
        }
        return ret;
    }

}
