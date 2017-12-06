package main;

import java.util.Scanner;
import Item.ItemHandler;
import java.sql.SQLException;

public class UI {

    private ItemHandler itemHandler;
    private Scanner scanner;

    public UI(ItemHandler itemHandler, Scanner scanner) {
        this.itemHandler = itemHandler;
        this.scanner = scanner;
    }

    public void doCommand(String command) throws SQLException, ClassNotFoundException {
        if (command.contains("save") || command.equals("s")) {
            System.out.println("");
            itemHandler.saveItem(scanner);
        } else if (command.equals("browse") || command.isEmpty() || command.equals("b")) {
            itemHandler.getItems(scanner);
        } else if (command.equals("f")) {
            itemHandler.findOne(scanner);
        } else if (command.equals("add comment") || command.equals("a")) {
            itemHandler.addComment(scanner);
        } else if (command.matches("\\d+")) {
            if (itemHandler.getOne(Integer.parseInt(command)) != null) {
                System.out.println(itemHandler.getOne(Integer.parseInt(command)) + "\n");
            }
        } else {
            System.out.println("Command not recognized, try using one of the above mentioned commands! \n");
        }
    }

}
