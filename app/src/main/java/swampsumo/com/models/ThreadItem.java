package swampsumo.com.models;

/**
 * Created by babatundedennis on 9/11/16.
 */
public class ThreadItem {
    private String id;
    private String vendorId;
    private String vendorName;
    private String subject;
    private String date;
    private String vendorImageUrl;

    public ThreadItem(){

    }

    public ThreadItem(String id, String vendor_id,
                      String vendor_name, String subject,
                      String date){

        this.id = id;
        this.vendorId = vendor_id;
        this.vendorName = vendor_name;
        this.subject = subject;
        this.date = date;

    }

    public ThreadItem(String vendor_id,
                      String vendor_name, String subject,
                      String date){
        this.vendorId = vendor_id;
        this.vendorName = vendor_name;
        this.subject = subject;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setVendorImageUrl(String vendorImageUrl) {
        this.vendorImageUrl = vendorImageUrl;
    }

    public String getVendorImageUrl() {
        return vendorImageUrl;
    }
}
