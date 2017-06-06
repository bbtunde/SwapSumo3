package swampsumo.com.utils.u.helpers;

import android.app.Activity;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import swampsumo.com.database.DatabaseHandler;
import swampsumo.com.swapsumo.LoginActivity;


/**
 * Created by babatundedennis on 5/4/16.
 */


public class VolleyErrorHandler {


    public String VolleyErrorHandler(VolleyError error, Activity activity){

        String json = null;
        DatabaseHandler dh = DatabaseHandler.getInstance(activity);

        NetworkResponse response = error.networkResponse;

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
           json = "No internet connection";
        }
        else if (error instanceof AuthFailureError) {
            //TODO
            json  = "You've been logged out";
            dh.truncateTable(DatabaseHandler.TABLE_USER);

            Intent i = new Intent(activity, LoginActivity.class);
            i.putExtra(LoginActivity.MESSAGE,  "You've been logged out");
            activity.startActivity(i);
        }
        else if (error instanceof ServerError) {
            //TODO
            json =  new String(response.data);
            json = trimMessage(json, "message");

        }
        else if (error instanceof NetworkError) {
            //TODO
            json = "Unexpected network error";
        }
        else if (error instanceof ParseError) {
            //TODO
            json = "Unable to complete action.";
        }


        if(response != null){
            switch(response.statusCode){

                case 400:
                    json = new String(response.data);
                    json = trimMessage(json, "message");

                case 403:
                    json = new String(response.data);
                    //Log.e(Constants.TAG, "erro 403:" + json);
                case 500:
                    json = new String("Something went wrong.");
                    break;

                default:
                    json = new String(response.data);

            }
            //Additional cases
        }


        return json;
    }

    public String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }
}
