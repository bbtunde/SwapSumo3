package swampsumo.com.adapters;

/**
 * Created by babatundedennis on 3/18/17.
 */
import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import swampsumo.com.models.SingleItemModel;
import swampsumo.com.swapsumo.AppController;
import swampsumo.com.swapsumo.MainActivity;
import swampsumo.com.swapsumo.R;
import swampsumo.com.swapsumo.SwitchFragments;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;
    private boolean notComplete;
    private SwitchFragments callback;
    private String batch_id;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList, String batch_id) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.callback = (SwitchFragments)context;
        this.batch_id = batch_id;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);
        notComplete = singleItem.isNotComplete();


        if(notComplete){
            Picasso.with(AppController.getContext())
                    .load(R.drawable.icon_plus)
                    .centerCrop()
                    .fit()
                    .placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha)
                    .error(R.drawable.abc_btn_colored_material)
                    .into(holder.itemImage);
        }
        else{
            Picasso.with(AppController.getContext())
                    .load(singleItem.getItemPic())
                    .centerCrop()
                    .fit()
                    .placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha)
                    .error(R.drawable.abc_btn_colored_material)
                    .into(holder.itemImage);
        }



        //holder.tvTitle.setText(singleItem.getName());


       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            //this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(notComplete){
                        callback.toManageItemInBatch(
                                true,
                                batch_id
                        );
                    }
                    else{
                        Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    }


                }
            });


        }

    }

}