package Item;

import Data.Database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class ItemHandler {

    private ItemController itemController;

    public ItemHandler(Database db, ItemController itemController) {
        this.itemController = itemController;
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

            if(answerString.equals("no")) {
                return;
            } else if (answerString.equals("yes")) {
                answer = true;
            } else {
                System.out.println("Command not recognized try [Yes or no]");
            }
        }

        System.out.println("Which number?");
        int index = Integer.parseInt(scanner.nextLine());
        Item wantedItem = (Item) listedItems.get(index);
        System.out.println(wantedItem);


        if(wantedItem.getUrl() == null)  {
            System.out.println("Would you like to open item's link in your browser? [Yes or no]");
            if(scanner.nextLine().toLowerCase().equals("yes")) {
                itemController.openItemLink(wantedItem.getUrl());
            }
        }

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



    //FIX THIS!!
    public void findOne(Scanner scanner) throws SQLException, ClassNotFoundException {
        System.out.println("Test");
    }

}
