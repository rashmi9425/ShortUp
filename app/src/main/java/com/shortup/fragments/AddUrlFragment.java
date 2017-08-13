package com.shortup.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.shortup.R;
import com.shortup.models.pojos.ResponsePojo;
import com.shortup.network.HttpGetClient;
import com.shortup.services.UrlShortenService;

import static android.R.attr.type;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddUrlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddUrlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUrlFragment extends Fragment implements UrlShortenService.UrlShortenServiceInterface,
        View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    private Button bAddUrl;
    private Button buttonFloat;
    private EditText etUrl;
    private ProgressBar progressBar;
    private TextView tvShortUrl;

    private Context context;

    private UrlShortenService shortenService;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddUrlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUrlFragment newInstance() {
        AddUrlFragment fragment = new AddUrlFragment();
        return fragment;
    }

    public AddUrlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_url, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        etUrl = (EditText)view.findViewById(R.id.etUrl);
        bAddUrl = (Button)view.findViewById(R.id.bAddUrl);
        buttonFloat = (Button)view.findViewById(R.id.buttonFloat);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        tvShortUrl = (TextView)view.findViewById(R.id.tvShortUrl);
        progressBar.setVisibility(View.INVISIBLE);

        bAddUrl.setOnClickListener(this);

        shortenService = new UrlShortenService(context, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onUrlShortenServiceResponseReceived(ResponsePojo responsePojo) {
        tvShortUrl.setText(responsePojo.getData().getUrl());
    }

    @Override
    public void onUrlShortenServiceError(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bAddUrl){
            if (shortenService != null  && etUrl.getText().toString() != null && etUrl.getText().toString().length() > 0){
                Log.v(">>>>>>>>>>>>>>>", Uri.encode(etUrl.getText().toString()));
                shortenService.shorten(etUrl.getText().toString());
                //https://api-ssl.bitly.com/v3/shorten?access_token=512b33cc519eeb5a59829f12c823ada432f37503&longUrl=http%3A%2F%2Fgoogle.com%2F&format=json
            /*   Thread t = new Thread(new Runnable() {
                   @Override
                   public void run() {
                       HttpGetClient client = new HttpGetClient();
                        client.setUrl(etUrl.getText().toString());
                        client.sendGetRequest();
                       ResponsePojo output = new GsonBuilder().create().fromJson(client.getResponse(),ResponsePojo.class);
                        //Log.v("###################", client.getResponseCode());
                        Log.v("###################", output.getData().getUrl());
                       if(client.getResponseCode()==200){
                           tvShortUrl.setText(output.getData().getUrl());
                       }
                       else
                           Toast.makeText(v.getContext(),"failure", Toast.LENGTH_SHORT).show();

                    }
                });
                t.start();*/

            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onAddUrlFragmentInteraction();
    }
}
