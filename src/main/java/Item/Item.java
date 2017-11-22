package Item;

public class Item {

    public String title;
    public String author;
    public String url;
    public String isbn;
    public String type;
    public String description;
    public boolean is_read;

    public Item(String title, String author, String URL, String isbn, String type, String description, boolean read) {
        this.title = title;
        this.author = author;
        this.url = URL;
        this.isbn = isbn;
        this.type = type;
        this.description = description;
        this.is_read = read;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + url + ", " + type;
    }
}
