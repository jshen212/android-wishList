package model;

/**
 * Created by JeffHome on 10/9/16.
 */

public class MyWish {

    public String title;
    public String content;
    public String recorddate;
    public int itemId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecordDate() {
        return recorddate;
    }

    public void setRecordDate(String recorddate) {
        this.recorddate = recorddate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
