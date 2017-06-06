package swampsumo.com.models;

/**
 * Created by babatundedennis on 3/18/17.
 */
public class SingleItemModel {


    private String batchId;
    private String subject;
    private String description;
    private String price;
    private String itemPic;
    private String dateAdded;
    private int status;
    private boolean notComplete;



    public boolean isNotComplete() {
        return notComplete;
    }

    public void setNotComplete(boolean notComplete) {
        this.notComplete = notComplete;
    }

    public SingleItemModel() {
    }

    public SingleItemModel(boolean notComplete) {
        this.notComplete = notComplete;

    }

    public SingleItemModel(String batchId, String subject, String description, String price,
                           String dateAdded, int status, String itemPic, boolean notComplete) {
        this.batchId = batchId;
        this.subject = subject;
        this.description = description;
        this.price = price;
        this.dateAdded = dateAdded;
        this.status = status;
        this.itemPic = itemPic;
        this.notComplete = notComplete;
    }

    public SingleItemModel(String subject, String description, String price,
                           String dateAdded, int status, String itemPic, boolean notComplete) {

        this.subject = subject;
        this.description = description;
        this.price = price;
        this.dateAdded = dateAdded;
        this.status = status;
        this.itemPic = itemPic;
        this.notComplete = notComplete;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
