package com.shortup.activities;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.shortup.R;
import com.shortup.fragments.AddUrlFragment;

public class HomeScreen extends AppCompatActivity implements AddUrlFragment.OnFragmentInteractionListener{

    private FrameLayout fl_hs_fragment_container;

    public static FragmentManager mSupportFragmentManager;
    public static Context mApplicationContext;
    public static Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initialize();

        Fragment mAddUrlFragment = AddUrlFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_hs_fragment_container, mAddUrlFragment)
                .addToBackStack(null)
                .commit();
    }

    private void initialize() {

        mSupportFragmentManager = getSupportFragmentManager();
        mApplicationContext = getApplicationContext();
        mResources = getResources();
        fl_hs_fragment_container = (FrameLayout)findViewById(R.id.fl_hs_fragment_container);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddUrlFragmentInteraction() {
    }

}
