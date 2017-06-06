package swampsumo.com.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import swampsumo.com.models.ThreadItem;
import swampsumo.com.swapsumo.R;
import swampsumo.com.utils.u.helpers.Constants;

/**
 * Created by babatundedennis on 9/11/16.
 */
public class ThreadAdapter extends ArrayAdapter<ThreadItem> {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ThreadItem> feedItems;

    private String Id;
    private String subject;
    private String vendor_name;
    private String dateUpdated;

    public ThreadAdapter(Activity activity, ArrayList<ThreadItem> feedItems
            //,String id, String vendor_name, String subject, String date_updated
    ) {
        super(activity, 0, feedItems);
        this.activity = activity;
        /*this.Id = id;
        this.vendor_name = vendor_name;
        this.dateUpdated = date_updated;
        this.subject = subject;*/

    }

    static class ViewHolder {
        private TextView Subject;
        private TextView VendorName;
        private TextView DateUpdated;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ThreadItem thread = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.thread_item, parent, false);
            holder.Subject = (TextView)convertView.findViewById(R.id.subject);
            holder.VendorName = (TextView) convertView.findViewById(R.id.vendor_name);
            holder.DateUpdated = (TextView)convertView.findViewById(R.id.date_updated);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Subject.setText(thread.getSubject());
        holder.VendorName.setText(thread.getVendorName());
        holder.DateUpdated.setText(thread.getDate());

        final ImageView feedImageView = (ImageView)convertView.findViewById(R.id.vendor_image);

        Log.e(Constants.TAG, thread.getVendorImageUrl());
        Picasso.with(getContext())
                .load(thread.getVendorImageUrl())
                .centerCrop()
                .fit()
                .placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha)
                .error(R.drawable.abc_btn_colored_material)
                .into(feedImageView);

        return convertView;
    }

}
