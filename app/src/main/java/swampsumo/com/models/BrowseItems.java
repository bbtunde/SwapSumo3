package swampsumo.com.models;

/**
 * Created by babatundedennis on 12/11/16.
 */
public class BrowseItems {

    private String Id;
    private String dateCreated;
    private String batchPic;
    private String subject;
    private String itemCount;
    private String vendor;
    private String distance;
    private boolean trading;
    private String description;

    public BrowseItems(){

    }

    public BrowseItems(String id, String dateCreated, String batchPic, String subject, String description, String itemCount, String vendor, String distance, boolean trading) {
        Id = id;
        this.dateCreated = dateCreated;
        this.batchPic = batchPic;
        this.subject = subject;
        this.itemCount = itemCount;
        this.vendor = vendor;
        this.distance = distance;
        this.trading = trading;
        this.description = description;
    }

    public BrowseItems(String dateCreated, String batchPic, String subject, String description,
                       String itemCount, String vendor, String distance, boolean trading) {
        this.dateCreated = dateCreated;
        this.batchPic = batchPic;
        this.subject = subject;
        this.itemCount = itemCount;
        this.vendor = vendor;
        this.distance = distance;
        this.trading = trading;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return Id;
    }

    public boolean getTrading() {
        return trading;
    }

    public void setTrading(boolean trading) {
        this.trading = trading;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getBatchPic() {
        return batchPic;
    }

    public void setBatchPic(String batchPic) {
        this.batchPic = batchPic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
}
