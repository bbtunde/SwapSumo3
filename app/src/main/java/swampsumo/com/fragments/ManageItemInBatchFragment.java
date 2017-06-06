package swampsumo.com.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import swampsumo.com.swapsumo.R;
import swampsumo.com.utils.u.helpers.Constants;
import swampsumo.com.utils.u.helpers.Utility;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.mikepenz.iconics.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManageItemInBatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManageItemInBatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageItemInBatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2, price_unformatted;
    private EditText name, description,price;
    private String userChoosenTask;
    private OnFragmentInteractionListener mListener;
    private CircleImageView item_image;
    private Dialog dialog_waiting;
    private static String fileName;
    private String ImgPath, fileNameServer;
    private static final int FILE_SELECTED = 1;
    private static final String MY_BUCKET ="boxment";
    private TransferObserver observer;
    private TransferUtility transferUtility;
    private Utility util;
    public ManageItemInBatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageItemInBatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageItemInBatchFragment newInstance(String param1, String param2) {
        ManageItemInBatchFragment fragment = new ManageItemInBatchFragment();
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

        transferUtility = util.getTransferUtility(getContext());
        util = new Utility();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manage_item_in_batch, container, false);
        item_image = (CircleImageView)v.findViewById(R.id.item_pic);
        Button save = (Button)v.findViewById(R.id.save_item);
        price = (EditText)v.findViewById(R.id.price);
        name = (EditText)v.findViewById(R.id.name);
        description = (EditText)v.findViewById(R.id.description);

        price.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    price.removeTextChangedListener(this);

                    String replaceable = String.format("[%s,.\\s]",   NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    double parsed;
                    try {
                        parsed = Double.parseDouble(cleanString);
                    } catch (NumberFormatException e) {
                        parsed = 0.00;
                    }
                    NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    formatter.setMaximumFractionDigits(0);
                    String formatted = formatter.format((parsed));

                    current = formatted;
                    price_unformatted = s.toString();
                    price.setText(formatted);
                    price.setSelection(formatted.length());
                    price.addTextChangedListener(this);
                    price.addTextChangedListener(this);
                }
            }
        });

        item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Constants.TAG, "clicked");
                OpenGallery();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Constants.TAG, "Price " + price_unformatted);
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onManageItemInBatchFragmentInteraction(uri);
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
        void onManageItemInBatchFragmentInteraction(Uri uri);
    }

    private Uri getUri() {
        String state = Environment.getExternalStorageState();
        if(!state.equalsIgnoreCase(Environment.MEDIA_MOUNTED))
            return MediaStore.Images.Media.INTERNAL_CONTENT_URI;

        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    private class UploadToS3Bucket extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            if (isCancelled()){
                cancel(true);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            final File file = new File(fileName);
                    Log.e(Constants.TAG, "running");
                    observer = transferUtility.upload(MY_BUCKET, file.getName(), file);
                    observer.setTransferListener(new TransferListener() {
                        @Override
                        public void onStateChanged(int i, TransferState transferState) {

                            if (transferState.COMPLETED.equals(observer.getState())){
                                fileNameServer = file.getName();
                                UploadEnded();
                            }
                        }

                        @Override
                        public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                        }

                        @Override
                        public void onError(int id, Exception e) {
                            UploadEnded();
                            new Utility().onNotify("Unable to upload. Network error", getContext());
                            cancel(true);
                        }
                    });


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }

    }

    private void OpenGallery(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), FILE_SELECTED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FILE_SELECTED) {

                try {
                    Uri originalUri = data.getData();
                    String pathsegment[] = originalUri.getLastPathSegment().split(":");
                    String id = pathsegment[0];
                    final String[] imageColumns = { MediaStore.Images.Media.DATA };

                    Uri uri = getUri();
                    Cursor imageCursor = getContext().getContentResolver().query(uri, imageColumns,
                            MediaStore.Images.Media._ID + "=" + id, null, null);

                    if (imageCursor.moveToFirst()) {
                        fileName = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    }
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                    item_image.setImageBitmap(bm);

                } catch (Exception e) {
                    util.onNotify("Failed to get image", getContext());
                    Log.e(Constants.TAG, e.getMessage());
                    UploadEnded();
                }

            }
        }

    }

    private void UploadEnded(){

        if(dialog_waiting != null) {
            dialog_waiting.dismiss();
        }

        if(observer != null){
            transferUtility.cancel(observer.getId());
        }
    }

}
