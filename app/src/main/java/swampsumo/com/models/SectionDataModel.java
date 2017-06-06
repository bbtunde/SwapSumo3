package swampsumo.com.models;

/**
 * Created by babatundedennis on 3/18/17.
 */
import java.util.ArrayList;

public class SectionDataModel {



    private String headerTitle;
    private String batchId;
    private ArrayList<SingleItemModel> allItemsInSection;


    public SectionDataModel() {

    }

    public SectionDataModel(String headerTitle, String batchId, ArrayList<SingleItemModel> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItemModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<SingleItemModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}
