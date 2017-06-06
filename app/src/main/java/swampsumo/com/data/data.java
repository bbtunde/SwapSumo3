package swampsumo.com.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by babatundedennis on 9/11/16.
 */
public class data {

    public JSONArray thread() {

    JSONObject thread1 = new JSONObject();
    JSONObject thread2 = new JSONObject();
    JSONObject thread3 = new JSONObject();
        try{
            thread1.put("id", "1");
            thread1.put("vendor_name", "Timothy Brad");
            thread1.put("date_updated", "03/09/2016");
            thread1.put("vendor_id", "34");
            thread1.put("vendor_image_url", "https://qph.ec.quoracdn.net/main-thumb-90740453-50-vatfjpsrhfjosfjqptlzoqjcofarklcr.jpeg");
            thread1.put("subject", "Shoes from Dubai");

            thread2.put("id", "2");
            thread2.put("vendor_name", "Eden Hazard");
            thread2.put("date_updated", "21/08/2016");
            thread2.put("vendor_id", "35");
            thread2.put("vendor_image_url", "https://qph.ec.quoracdn.net/main-thumb-33351438-50-kdvhdlegmgfjanowpsbakqwxqjzruxsc.jpeg");
            thread2.put("subject", "Football Jersey from United States");


            thread3.put("id", "3");
            thread3.put("vendor_name", "Christiano Ronaldo");
            thread3.put("date_updated", "10/09/2016");
            thread3.put("vendor_id", "36");
            thread3.put("vendor_image_url", "https://qph.ec.quoracdn.net/main-thumb-177957107-50-fcvzbvdrwqptdvwvfkjrbsyknwpusswj.jpeg");
            thread3.put("subject", "Lovely pair of pants");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(thread1);
        jsonArray.put(thread2);
        jsonArray.put(thread3);

        return jsonArray;
    }

    public JSONArray my_batches() {

        JSONObject thread1 = new JSONObject();
        JSONObject thread2 = new JSONObject();
        JSONObject thread3 = new JSONObject();
        JSONObject thread4 = new JSONObject();

        try{
            thread1.put("id", "1");
            thread1.put("date_created", "03/09/2016");
            thread1.put("batch_main_url", "http://demandware.edgesuite.net/aaqw_prd/on/demandware.static/-/Sites-DC-US-Library/default/dw185d0bff/dc/category-assets/marketing-landing/img/skate/tile1.jpg");
            thread1.put("subject", "Shoes from Dubai");

            thread2.put("id", "2");
            thread2.put("date_created", "03/09/2016");
            thread2.put("batch_main_url", "http://www.prodirectsoccer.com/us/productimages/V3_1_Main/88378.jpg");
            thread2.put("subject", "Arsenal Away Jersey (Blue)");

            thread3.put("id", "3");
            thread3.put("date_created", "03/09/2016");
            thread3.put("batch_main_url", "http://www.handbagsellers.com/wp-content/uploads/2014/02/prada-pink-bag-600x400.png");
            thread3.put("subject", "A collection of fake bags from Rio");

            thread4.put("id", "4");
            thread4.put("date_created", "12/09/2016");
            thread4.put("batch_main_url", "http://www.bicycling.com/sites/bicycling.com/files/articles/2015/12/rain-cap-main.jpg");
            thread4.put("subject", "Useful hats for all seasons");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(thread1);
        jsonArray.put(thread2);
        jsonArray.put(thread3);
        jsonArray.put(thread4);

        return jsonArray;
    }


    public JSONArray my_items() {

        JSONObject thread1 = new JSONObject();
        JSONObject thread2 = new JSONObject();
        JSONObject thread3 = new JSONObject();
        JSONObject thread4 = new JSONObject();

        try{
            thread1.put("id", "i1");
            thread1.put("date_added", "03/09/2016");
            thread1.put("item_pic", "http://demandware.edgesuite.net/aaqw_prd/on/demandware.static/-/Sites-DC-US-Library/default/dw185d0bff/dc/category-assets/marketing-landing/img/skate/tile1.jpg");
            thread1.put("subject", "A pair of Hudson");
            thread1.put("description", "I got this from the marketplace in town");
            thread1.put("price", "100");

            thread2.put("id", "i2");
            thread2.put("date_added", "03/09/2016");
            thread2.put("item_pic", "http://demandware.edgesuite.net/aaqw_prd/on/demandware.static/-/Sites-DC-US-Library/default/dw185d0bff/dc/category-assets/marketing-landing/img/skate/tile1.jpg");
            thread2.put("subject", "Ted Baker shoes");
            thread2.put("description", "Saw this pair in at the airport");
            thread2.put("price", "120");


            thread3.put("id", "i3");
            thread3.put("date_added", "03/09/2016");
            thread3.put("item_pic", "http://demandware.edgesuite.net/aaqw_prd/on/demandware.static/-/Sites-DC-US-Library/default/dw185d0bff/dc/category-assets/marketing-landing/img/skate/tile1.jpg");
            thread3.put("subject", "Tory Burch Green Flats");
            thread3.put("description", "These flat shoes are really comfortable.");
            thread3.put("price", "90");


            thread4.put("id", "i4");
            thread4.put("date_added", "03/09/2016");
            thread4.put("item_pic", "http://demandware.edgesuite.net/aaqw_prd/on/demandware.static/-/Sites-DC-US-Library/default/dw185d0bff/dc/category-assets/marketing-landing/img/skate/tile1.jpg");
            thread4.put("subject", "D&G with studs");
            thread4.put("description", "My sister gave me this but not my size");
            thread4.put("price", "140");



        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(thread1);
        jsonArray.put(thread2);
        jsonArray.put(thread3);
        jsonArray.put(thread4);

        return jsonArray;
    }


    public JSONArray browse() {

        JSONObject thread1 = new JSONObject();
        JSONObject thread2 = new JSONObject();
        JSONObject thread3 = new JSONObject();
        JSONObject thread4 = new JSONObject();

        try{
            thread1.put("id", "1");
            thread1.put("date_created", "03/09/2016");
            thread1.put("batch_main_url", "http://demandware.edgesuite.net/aaqw_prd/on/demandware.static/-/Sites-DC-US-Library/default/dw185d0bff/dc/category-assets/marketing-landing/img/skate/tile1.jpg");
            thread1.put("subject", "Shoes from Dubai");
            thread1.put("description", "Not needed anymore");
            thread1.put("items_count", "4");
            thread1.put("trading", true);
            thread1.put("vendor", "James Gordon");
            thread1.put("distance", "1km");

            thread2.put("id", "2");
            thread2.put("date_created", "03/09/2016");
            thread2.put("batch_main_url", "http://www.prodirectsoccer.com/us/productimages/V3_1_Main/88378.jpg");
            thread2.put("subject", "Arsenal Away Jersey (Blue)");
            thread2.put("description", "Sometimes you wake up and change clubs");
            thread2.put("items_count", "3");
            thread2.put("trading", false);
            thread2.put("vendor", "Harvey Dent");
            thread2.put("distance", "3km");

            thread3.put("id", "3");
            thread3.put("date_created", "03/09/2016");
            thread3.put("batch_main_url", "http://www.handbagsellers.com/wp-content/uploads/2014/02/prada-pink-bag-600x400.png");
            thread3.put("subject", "A collection of fake bags from Rio");
            thread3.put("description", "Having a clearout");
            thread3.put("items_count", "5");
            thread3.put("trading", true);
            thread3.put("vendor", "Luke Cage");
            thread3.put("distance", "5km");

            thread4.put("id", "4");
            thread4.put("date_created", "12/09/2016");
            thread4.put("batch_main_url", "http://www.bicycling.com/sites/bicycling.com/files/articles/2015/12/rain-cap-main.jpg");
            thread4.put("subject", "Useful hats for all seasons");
            thread4.put("description", "These are not useful to me anymore");
            thread4.put("items_count", "2");
            thread4.put("trading", false);
            thread4.put("vendor", "Jessica Jones");
            thread4.put("distance", "20km");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(thread1);
        jsonArray.put(thread2);
        jsonArray.put(thread3);
        jsonArray.put(thread4);

        return jsonArray;
    }
}
