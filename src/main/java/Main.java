
import Data.Database;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            Database.connect();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getException());
        }
    }
}
