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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inmobi.ads.InMobiNative;
import com.shortup.R;
import com.shortup.managers.ad_manager.AdManager;
import com.shortup.models.pojos.ResponsePojo;
import com.shortup.network.HttpGetClient;
import com.shortup.services.UrlShortenService;
import com.shortup.utils.GlobalConstant;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

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
        View.OnClickListener, AdManager.AdManagerInterface {

    private OnFragmentInteractionListener mListener;

    private Button bAddUrl;
    private Button buttonFloat;
    private EditText etUrl;
    private ProgressBar progressBar;
    private TextView tvShortUrl;
    private TextView tvAdHeader;
    private ImageView ivAdImage;

    private Activity activity;
    private UrlShortenService shortenService;
    private AdManager adManager;

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
        return inflater.inflate(R.layout.fragment_add_url, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        etUrl = (EditText)view.findViewById(R.id.etUrl);
        bAddUrl = (Button)view.findViewById(R.id.bAddUrl);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        tvShortUrl = (TextView)view.findViewById(R.id.tvShortUrl);
        tvAdHeader = (TextView)view.findViewById(R.id.tvAdHeader);
        ivAdImage = (ImageView)view.findViewById(R.id.ivAdImage);
        progressBar.setVisibility(View.INVISIBLE);
        bAddUrl.setOnClickListener(this);

        shortenService = new UrlShortenService(this);
        adManager = new AdManager();

        if (this.activity != null)
            adManager.init(this.activity, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
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
    public void onUrlShortenServiceResponseReceived(JsonObject responseJson) {
        progressBar.setVisibility(View.INVISIBLE);
        adManager.fetchAd();
        if (responseJson.get("status_code").getAsInt() == 200 && responseJson.get("status_txt").toString().equalsIgnoreCase("OK"))
            tvShortUrl.setText(responseJson.get("data").getAsJsonObject().get("url").getAsString());
        else
            Toast.makeText(this.getActivity(), "Invalid URL !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUrlShortenServiceError(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
        adManager.fetchAd();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bAddUrl){
            ivAdImage.setVisibility(View.VISIBLE);
            if (shortenService != null  && etUrl.getText().toString() != null && etUrl.getText().toString().length() > 0){
                progressBar.setVisibility(View.VISIBLE);
                shortenService.shorten(etUrl.getText().toString());
            }
        }
    }

    @Override
    public void onShowAd(JSONObject content) {
        ivAdImage.setVisibility(View.VISIBLE);
        try {
            if (tvAdHeader != null && ivAdImage != null){
                tvAdHeader.setText(content.get("title").toString() + " : " + content.get("description").toString());
                Picasso.with(this.activity)
                        .load(content.getJSONObject("screenshots").get("url").toString())
                        .centerInside()
                        .fit()
                        .into(ivAdImage);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRemoveAd() {
        ivAdImage.setVisibility(View.INVISIBLE);
    }

    public interface OnFragmentInteractionListener {
        public void onAddUrlFragmentInteraction();
    }
}
