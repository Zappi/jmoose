package main;

import java.util.Scanner;

import Comment.CommentHandler;
import Item.ItemHandler;
import java.sql.SQLException;


public class UI {

    private ItemHandler itemHandler;
    private Scanner scanner;
    private CommentHandler commentHandler;

    public UI(ItemHandler itemHandler, Scanner scanner, CommentHandler commentHandler) {
        this.itemHandler = itemHandler;
        this.scanner = scanner;
        this.commentHandler = commentHandler;
    }

    public void doCommand(String command) throws SQLException, ClassNotFoundException {
        if (command.contains("save") || command.equals("s")) {
            System.out.println("");
            itemHandler.saveItem(scanner);
        } else if (command.equals("browse") || command.isEmpty() || command.equals("b")) {
            itemHandler.getItems(scanner);
        } else if (command.equals("f") || (command.equals("filter"))) {
                itemHandler.filterSearch(scanner);
        } else if (command.equals("g") || command.equals("get")) {
            itemHandler.findOne(scanner);
        } else if (command.matches("\\d+")) {
            if (itemHandler.getOne(Integer.parseInt(command)) != null) {
                System.out.println(itemHandler.getOne(Integer.parseInt(command)) + "\n");
            }
        } else {
            System.out.println("Command not recognized, try using one of the mentioned commands above! \n");
        }

    }

}
