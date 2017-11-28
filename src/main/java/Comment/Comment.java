package Comment;
import Item.Item;

public class Comment {
    public String comment;
    public Item item;

    public Comment (String Comment, Item item){
        this.comment = comment;
        this.item = item;
    }

    @Override
    public String toString() {
        return comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
