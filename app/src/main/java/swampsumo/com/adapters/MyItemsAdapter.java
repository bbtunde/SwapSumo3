package swampsumo.com.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import swampsumo.com.models.MyItems;
import swampsumo.com.swapsumo.AddItemToBatchActivity;
import swampsumo.com.swapsumo.R;

/**
 * Created by babatundedennis on 10/1/16.
 */
public class MyItemsAdapter extends ArrayAdapter<MyItems> {

    private Activity activity;
    private String batch_id;
    private String batch_name;

    public MyItemsAdapter(Activity activity, ArrayList<MyItems> feedItems, String batch_id, String batch_name
    ) {
        super(activity, 0, feedItems);
        this.activity = activity;
        this.batch_id = batch_id;
        this.batch_name = batch_name;
    }

    static class ViewHolder {
        private TextView getGetDateAdded, getSubject, getPrice;
        private TextView getDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyItems myitem = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_items_item, parent, false);
            holder.getGetDateAdded = (TextView)convertView.findViewById(R.id.date_added);
            holder.getPrice = (TextView)convertView.findViewById(R.id.price);
            holder.getSubject = (TextView)convertView.findViewById(R.id.subject);
            holder.getDescription = (TextView)convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getSubject.setText(myitem.getSubject());
        holder.getGetDateAdded.setText("Added " +  myitem.getDateAdded());
        holder.getPrice.setText(myitem.getPrice());
        holder.getDescription.setText(myitem.getDescription());

        final ImageView feedImageView = (ImageView)convertView.findViewById(R.id.item_pic);


        Picasso.with(getContext())
                .load(myitem.getItemPic())
                .centerCrop()
                .fit()
                .placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha)
                .error(R.drawable.abc_btn_colored_material)
                .into(feedImageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, AddItemToBatchActivity.class);
                i.putExtra(AddItemToBatchActivity.BATCH_ID, batch_id);
                i.putExtra(AddItemToBatchActivity.BATCH_NAME, batch_name);
                i.putExtra(AddItemToBatchActivity.EDIT, true);
                i.putExtra(AddItemToBatchActivity.ITEM_NAME, myitem.getSubject());
                i.putExtra(AddItemToBatchActivity.ITEM_PRICE, myitem.getPrice());
                i.putExtra(AddItemToBatchActivity.ITEM_DATE_ADDED, myitem.getDateAdded());
                i.putExtra(AddItemToBatchActivity.ITEM_DESCRIPTION, myitem.getDescription());
                activity.startActivity(i);

            }
        });
        return convertView;
    }
}
