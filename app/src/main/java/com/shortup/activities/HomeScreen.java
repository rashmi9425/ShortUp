package com.shortup.activities;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.inmobi.sdk.InMobiSdk;
import com.shortup.R;
import com.shortup.fragments.AddUrlFragment;

public class HomeScreen extends AppCompatActivity implements AddUrlFragment.OnFragmentInteractionListener{

    public static Context mApplicationContext;
    public static Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        InMobiSdk.init(HomeScreen.this, "2ab350952a3440528b7544a8a4b9ba15");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Fragment mAddUrlFragment = AddUrlFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_hs_fragment_container, mAddUrlFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onAddUrlFragmentInteraction() {
    }

}
