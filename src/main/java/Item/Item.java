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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
