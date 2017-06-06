package swampsumo.com.models;

/**
 * Created by babatundedennis on 9/18/16.
 */
public class MyBatches {


    private String Id;
    private String dateCreated;
    private String description;
    private String subject;
    private String userId;
    private int status;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MyBatches(){

    }

    public MyBatches(String id, String user_id, String subject, String description, int status, String dateCreated) {
        Id = id;
        this.dateCreated = dateCreated;
        this.description = description;
        this.subject = subject;
        this.status = status;
        this.userId = user_id;
    }

    public MyBatches(String user_id, String subject, String description, int status, String dateCreated) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.subject = subject;
        this.status = status;
        this.userId = user_id;
    }

    public String getId() {
        return Id;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
