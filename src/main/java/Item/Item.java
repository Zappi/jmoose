package Item;

public class Item {

    private int id;
    private String title;
    private String author;
    private String url;
    private String isbn;
    private String type;
    private String description;
    private boolean is_read;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item(int id, String title, String author, String URL, String isbn, String type, String description, boolean read) {
        this.id = id;
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
        String printout = "";
        if (title != null) {
            printout += title + ", ";
        }
        if (author != null) {
            printout += author + ", ";
        }
        if (url != null) {
            printout += url + ", ";
        }
        if (isbn != null) {
            printout += isbn + ", ";
        }
        if (type != null) {
            printout += type + ", ";
        }
        if (description != null){
            printout += description + ", ";
        }
        if (is_read){
            printout += "read";
        } else {
            printout += "unread";
        }
        return printout;
    }

    public String printForBrowse() {
        String printout = "";
        if (title != null) {
            printout += title + ", ";
        }
        if (author != null) {
            printout += author + ", ";
        }
        if (url != null) {
            printout += url + ", ";
        }
        if (type != null) {
            printout += type + ", ";
        }
        return printout;
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
