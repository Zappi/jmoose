package main;

import Dao.ItemDao;
import Data.Database;
import Item.Item;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ItemController {

    public ItemDao itemDao;
    private Database db;

    public ItemController(Database db) {
        this.itemDao = new ItemDao(db);
    }


    public void browseItems(Scanner scanner) throws SQLException, ClassNotFoundException {
        List<Item> allItems = itemDao.findAll();
        HashMap<Integer, Item> listedItems = new HashMap<>();
        int i = 1;
        for (Item item : allItems) {
            listedItems.put(i, item);
            System.out.println(i + " " + item);
            i++;
        }
        selectSingleItemFromTheList(scanner, listedItems);
    }

    private void selectSingleItemFromTheList(Scanner scanner, HashMap listedItems) throws SQLException, ClassNotFoundException {
        System.out.println("Would you like to see the single item info? (Yes or no)");
        String answer = scanner.nextLine().toLowerCase();

        if(answer.equals("no")) {
            return;
        }

        System.out.println("Which number?");
        int index = Integer.parseInt(scanner.nextLine());
        Item wantedItem = (Item) listedItems.get(index);

        System.out.println(itemDao.findOneByTitle(wantedItem.getTitle()));

    }


    //FIX THIS!!
    public void findOne(Scanner scanner) throws SQLException, ClassNotFoundException {
        System.out.println("Test");
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
        itemDao.save(title, author, url, isbn, type, description);
        System.out.println("Item saved successfully!");
    }
}
