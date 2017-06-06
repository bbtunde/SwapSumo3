package swampsumo.com.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import swampsumo.com.adapters.ThreadAdapter;
import swampsumo.com.data.data;
import swampsumo.com.models.ThreadItem;
import swampsumo.com.swapsumo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThreadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThreadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThreadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private GetThread getThread;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ThreadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThreadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThreadFragment newInstance(String param1, String param2) {
        ThreadFragment fragment = new ThreadFragment();
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
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getThread = new GetThread();
                getThread.execute();
            }
        }, 3000);

        */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thread, container, false);
        listView = (ListView)v.findViewById(R.id.listView);

        final data threadLD = new data();

        try {
            parseFeed(threadLD.thread());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return v;

    }

    private void parseFeed(JSONArray feedArray) throws JSONException {

        final ArrayList<ThreadItem> arrayOfItems = new ArrayList<ThreadItem>();

        for (int i = 0; i < feedArray.length(); i++) {
            ThreadItem item = new ThreadItem();
            JSONObject feedObj = (JSONObject) feedArray.get(i);

            item.setDate(feedObj.getString("date_updated"));
            item.setSubject(feedObj.getString("subject"));
            item.setVendorName(feedObj.getString("vendor_name"));
            item.setId(feedObj.getString("id"));
            item.setVendorId(feedObj.getString("vendor_id"));
            item.setVendorImageUrl(feedObj.getString("vendor_image_url"));
            arrayOfItems.add(item);
        }

        if(getActivity() != null && arrayOfItems.size() > 0) {
            ThreadAdapter listAdapter = new ThreadAdapter(
                    getActivity(), arrayOfItems);

            listView.setAdapter(listAdapter);
        }

    }

    private class GetThread extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {

            if (isCancelled()) {
                cancel(true);
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            final data threadLD = new data();

            try {
                parseFeed(threadLD.thread());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(final Boolean result) {

        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onThreadFragmentInteraction(uri);
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
        void onThreadFragmentInteraction(Uri uri);
    }
}
