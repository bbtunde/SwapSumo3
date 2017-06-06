package swampsumo.com.utils.u.helpers;

import android.app.Activity;
import android.app.Dialog;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * Created by babatundedennis on 5/13/16.
 */
public class VolleyCacheRequest extends Request<NetworkResponse> {
    private final Response.Listener<NetworkResponse> mListener;
    private final Response.ErrorListener mErrorListener;
    private Activity activity;
    private Dialog dialog_waiting;

    public VolleyCacheRequest(Activity activity, int method, String url, Response.Listener<NetworkResponse> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mErrorListener = errorListener;
        this.activity = activity;
    }


    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);

        if (cacheEntry == null) {
            dialog_waiting = new Utility().Waiting(activity, "Please wait...");
            cacheEntry = new Cache.Entry();
        }

        final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
        final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
        long now = System.currentTimeMillis();
        final long softExpire = now + cacheHitButRefreshed;
        final long ttl = now + cacheExpired;

        cacheEntry.data = response.data;
        cacheEntry.softTtl = softExpire;
        cacheEntry.ttl = ttl;
        String headerValue;
        headerValue = response.headers.get("Date");

        if (headerValue != null) {
            cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }

        headerValue = response.headers.get("Last-Modified");

        if (headerValue != null) {
           cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }


        cacheEntry.responseHeaders = response.headers;
        return Response.success(response, cacheEntry);
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        if(dialog_waiting != null) {
            dialog_waiting.dismiss();
        }
        mListener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if(dialog_waiting != null) {
            dialog_waiting.dismiss();
        }
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(VolleyError error) {
        if(dialog_waiting != null) {
            dialog_waiting.dismiss();
        }
        mErrorListener.onErrorResponse(error);
    }
}
