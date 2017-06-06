package swampsumo.com.models;

/**
 * Created by babatundedennis on 10/1/16.
 */
public class MyItems {
    private String Id;
    private String dateAdded;
    private String itemPic;
    private String subject;
    private String description;
    private String price;
    private String batch_Id;
    private int status;

    public String getBatchId() {
        return batch_Id;
    }

    public void setBatchId(String batch_Id) {
        this.batch_Id = batch_Id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MyItems(){

    }

    public MyItems(String id, String dateAdded, String itemPic, String subject, String description,
                   String price, String batch_id, int status) {
        Id = id;
        this.dateAdded = dateAdded;
        this.itemPic = itemPic;
        this.subject = subject;
        this.description = description;
        this.price = price;
        this.batch_Id = batch_id;
        this.status = status;
    }

    public MyItems(String dateAdded, String itemPic, String subject, String description, String price,
                   String batch_id, int status) {
        this.dateAdded = dateAdded;
        this.itemPic = itemPic;
        this.subject = subject;
        this.description = description;
        this.price = price;
        this.batch_Id = batch_id;
        this.status = status;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
