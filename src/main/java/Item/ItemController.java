package Item;

import Dao.ItemDao;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ItemController {

    private ItemDao itemDao;

    public ItemController(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public HashMap<Integer, Item> browseItems() throws SQLException, ClassNotFoundException {
        List<Item> allItems = itemDao.findAll();
        HashMap<Integer, Item> listedItems = new HashMap<>();
        int i = 1;
        for (Item item : allItems) {
            listedItems.put(i, item);
            i++;
        }

        return listedItems;
    }

    public void save(String title, String author, String url, String isbn, String type, String description) {
        try {
            itemDao.save(title, author, url, isbn, type, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Item getOneItemByTtile(String title) {
        try {
            return itemDao.findOneByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openItemLink(String url) {
        String fixedURL = handleUrl(url);
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI itemURL = new URL(fixedURL).toURI();
            java.awt.Desktop.getDesktop().browse(itemURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String handleUrl(String url) {
        if(!url.startsWith("http://")) {
            url = "http://"+url;
        }
        return url;
    }


}
