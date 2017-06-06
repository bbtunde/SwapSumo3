package swampsumo.com.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import swampsumo.com.models.BrowseItems;
import swampsumo.com.swapsumo.R;

/**
 * Created by babatundedennis on 12/11/16.
 */
public class BrowseAdapter extends ArrayAdapter<BrowseItems> {

    private Activity activity;
    private Typeface font;


    public BrowseAdapter(Activity activity, ArrayList<BrowseItems> feedItems
    ) {
        super(activity, 0, feedItems);
        this.activity = activity;

    }

    static class ViewHolder {
        private TextView getGetDescription, getSubject, getItemsCount, getIconItemCount, getIconTraded, getIconPerson,
                getIconDistance,getVendor, getDistance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BrowseItems myitem = getItem(position);
        ViewHolder holder;

        font = Typeface.createFromAsset(this.activity.getAssets(), "fontawesome-webfont.ttf");

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.browse_item, parent, false);
            holder.getGetDescription = (TextView)convertView.findViewById(R.id.description);
            holder.getSubject = (TextView)convertView.findViewById(R.id.subject);
            holder.getItemsCount = (TextView)convertView.findViewById(R.id.item_count);
            holder.getIconTraded = (TextView)convertView.findViewById(R.id.icon_traded);
            holder.getIconItemCount = (TextView)convertView.findViewById(R.id.icon_item_count);
            holder.getDistance =  (TextView)convertView.findViewById(R.id.distance);
            holder.getVendor = (TextView)convertView.findViewById(R.id.vendor);
            holder.getIconPerson = (TextView)convertView.findViewById(R.id.icon_person);
            holder.getIconDistance = (TextView)convertView.findViewById(R.id.icon_distance);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getSubject.setText(myitem.getSubject());
        holder.getGetDescription.setText(myitem.getDescription());
        holder.getItemsCount.setText(myitem.getItemCount() + " items");
        holder.getDistance.setText(myitem.getDistance());
        holder.getVendor.setText(myitem.getVendor());

        holder.getIconTraded.setTypeface(font);
        holder.getIconPerson.setTypeface(font);
        holder.getIconDistance.setTypeface(font);

        if(myitem.getTrading()) {
            holder.getIconTraded.setText(String.valueOf((char) 0xf021));
        }

        holder.getIconPerson.setText(String.valueOf((char) 0xf007));
        holder.getIconDistance.setText(String.valueOf((char) 0xf124));
        //holder.getIconItemCount.setText(String.valueOf((char)0xf24d));

        final ImageView feedImageView = (ImageView)convertView.findViewById(R.id.item_pic);


        Picasso.with(getContext())
                .load(myitem.getBatchPic())
                .centerCrop()
                .fit()
                .placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha)
                .error(R.drawable.abc_btn_colored_material)
                .into(feedImageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(activity, AddItemToBatchActivity.class);
                i.putExtra(AddItemToBatchActivity.BATCH_ID, batch_id);
                i.putExtra(AddItemToBatchActivity.BATCH_NAME, batch_name);
                i.putExtra(AddItemToBatchActivity.EDIT, true);
                i.putExtra(AddItemToBatchActivity.ITEM_NAME, myitem.getSubject());
                i.putExtra(AddItemToBatchActivity.ITEM_PRICE, myitem.getPrice());
                i.putExtra(AddItemToBatchActivity.ITEM_DATE_ADDED, myitem.getDateAdded());
                i.putExtra(AddItemToBatchActivity.ITEM_DESCRIPTION, myitem.getDescription());
                activity.startActivity(i);
                */

            }
        });
        return convertView;
    }
}
