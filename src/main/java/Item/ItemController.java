package Item;

import Dao.ItemDao;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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

//    public Item findOneById(String id) {
//        return;
//    }

    public void save(String title, String author, String url, String isbn, String type, String description) throws SQLException, ClassNotFoundException {
        itemDao.save(title, author, url, isbn, type, description);
    }

    public void deleteByTitle(String title) throws SQLException, ClassNotFoundException {
        itemDao.delete(title);
    }

    public Item getOneItemByTitle(String title) throws SQLException, ClassNotFoundException {
        return itemDao.findOneByTitle(title);
    }

    
    public void openItemLink(String url) {
        String fixedURL = handleUrl(url);
//        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI itemURL = new URL(fixedURL).toURI();
            java.awt.Desktop.getDesktop().browse(itemURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String handleUrl(String url) {
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        return url;
    }


    public void changeReadStatus(String command, String title) throws SQLException, ClassNotFoundException {
        if (command.equals("r") || command.equals("read")) {
            itemDao.changeRead(true, title);
        } else if (command.equals("u") || command.equals("unread")) {
            itemDao.changeRead(false, title);
        }
    }
}
