package Comment;
import Item.Item;

public class Comment {
    public String comment;
    public int itemId;

    public Comment (String comment, int itemId){
        this.comment = comment;
        this.itemId = itemId;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
