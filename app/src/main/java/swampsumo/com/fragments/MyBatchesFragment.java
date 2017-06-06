package swampsumo.com.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import swampsumo.com.adapters.RecyclerViewDataAdapter;
import swampsumo.com.database.DatabaseHandler;
import swampsumo.com.models.MyBatches;
import swampsumo.com.models.MyItems;
import swampsumo.com.models.SectionDataModel;
import swampsumo.com.models.SingleItemModel;
import swampsumo.com.models.User;
import swampsumo.com.swapsumo.R;
import swampsumo.com.utils.u.helpers.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyBatchesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyBatchesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyBatchesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView listView;
    private TextView nobatch;

    private Context activity;

    private OnFragmentInteractionListener mListener;
    ArrayList<SectionDataModel> allSampleData;
    private DatabaseHandler dh;

    public MyBatchesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyBatchesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyBatchesFragment newInstance(String param1, String param2) {
        MyBatchesFragment fragment = new MyBatchesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dh = DatabaseHandler.getInstance(activity);
        allSampleData = new ArrayList<SectionDataModel>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_my_batches, container, false);
        nobatch = (TextView)v.findViewById(R.id.nobatch);
        listView = (RecyclerView)v.findViewById(R.id.my_batch);

        listView.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(activity, allSampleData);
        listView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(adapter);

        createDummyData();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMyItemFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void createDummyData() {
        List<MyBatches> batches  = dh.getBatches();
        List<MyItems> items  = dh.getItems();

        if(batches.size() == 0){
            listView.setVisibility(View.GONE);
            nobatch.setVisibility(View.VISIBLE);
        }
        else {
            for (int i = 0; i < batches.size(); i++) {
                SectionDataModel dm = new SectionDataModel();
                dm.setHeaderTitle(batches.get(i).getSubject());
                Log.e(Constants.TAG, "MyBatchesFragment BATCH_ID:" + batches.get(i).getId());
                dm.setBatchId(batches.get(i).getId());

                ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();

                if (items.size() == 0) {
                    singleItem.add(new SingleItemModel(true));
                }


                for (int j = 0; j < items.size(); j++) {

                    if (items.size() == j && j != 5) {
                        singleItem.add(new SingleItemModel(true));
                    } else {
                        singleItem.add(new SingleItemModel(
                                items.get(j).getBatchId(),
                                items.get(j).getSubject(),
                                items.get(j).getDescription(),
                                items.get(j).getPrice(),
                                items.get(j).getDateAdded(),
                                items.get(j).getStatus(),
                                items.get(j).getItemPic(),
                                false
                        ));
                    }

                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);

            }

            listView.setVisibility(View.VISIBLE);
            nobatch.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMyItemFragmentInteraction(Uri uri);
    }
}
