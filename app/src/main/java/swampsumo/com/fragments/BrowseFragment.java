package swampsumo.com.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import swampsumo.com.adapters.BrowseAdapter;
import swampsumo.com.data.data;
import swampsumo.com.models.BrowseItems;
import swampsumo.com.swapsumo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BrowseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BrowseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;

    private OnFragmentInteractionListener mListener;

    public BrowseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrowseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrowseFragment newInstance(String param1, String param2) {
        BrowseFragment fragment = new BrowseFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_browse, container, false);

        listView = (ListView)v.findViewById(R.id.browse_items);

        final data d = new data();

        try {
            parseFeed(d.browse());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        return v;
    }

    private void parseFeed(JSONArray feedArray) throws JSONException {

        final ArrayList<BrowseItems> arrayOfItems = new ArrayList<BrowseItems>();

        for (int i = 0; i < feedArray.length(); i++) {
            BrowseItems item = new BrowseItems();
            JSONObject feedObj = (JSONObject) feedArray.get(i);

            item.setDateCreated(feedObj.getString("date_created"));
            item.setSubject(feedObj.getString("subject"));
            item.setBatchPic(feedObj.getString("batch_main_url"));
            item.setId(feedObj.getString("id"));
            item.setItemCount(feedObj.getString("items_count"));
            item.setTrading(feedObj.getBoolean("trading"));
            item.setDistance(feedObj.getString("distance"));
            item.setDescription(feedObj.getString("description"));
            item.setVendor(feedObj.getString("vendor"));
            arrayOfItems.add(item);
        }

        if(getActivity() != null && arrayOfItems.size() > 0) {
            BrowseAdapter listAdapter = new BrowseAdapter(
                    getActivity(), arrayOfItems);

            //listView.setAdapter(listAdapter);


//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        vertical_recycler_view.setLayoutManager(mLayoutManager);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            listView.setAdapter(listAdapter);
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onBrowseFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
        void onBrowseFragmentInteraction(Uri uri);
    }
}
