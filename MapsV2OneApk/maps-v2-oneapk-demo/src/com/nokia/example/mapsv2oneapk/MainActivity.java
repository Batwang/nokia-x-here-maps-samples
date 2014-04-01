/*
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.nokia.example.mapsv2oneapk;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends FragmentActivity {
    private static final String HERE_LIBRARY = "com.here.android";
    /**
     * String array of meta-data keys in AndroidManifest.xml we validate on launch. These keys need to be defined for the application to actually work.
     */
    private static final String[] REQUIRED_KEYS_ARRAY = {"com.here.android.maps.appid", "com.here.android.maps.apptoken", "com.google.android.maps.v2.API_KEY"};
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * The view to show the ad.
     */
    private AdView adView;

    /* Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "YOUR_AD_UNIT_IT";

    private static final String MY_INTERSTITIAL_UNIT_ID = "YOUR_INTERSTITIAL_AD_UNIT_IT";

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (validateMetaData()) {
            addAd();
            addMapFragment();
            createInterstitial();
            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.missing_metadata, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

    }

    private void createInterstitial() {
        // Create the interstitial.
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(MY_INTERSTITIAL_UNIT_ID);

        // Create ad request.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Begin loading your interstitial.
        interstitial.loadAd(adRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /**
     * Called before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private void addAd() {
        // Create an ad.
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);

        // Add the AdView to the view hierarchy. The view will have no size
        // until the ad is loaded.
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("7249C9EBCC2A21FE34695E730C60DBFA")
                .addTestDevice("18915ACB769919CF2C428F8ED54C0FFD")
                .addTestDevice("AC5756D1952302521330EF49D1C0EC5D")
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private void addMapFragment() {
        Fragment fragment;
        if (hasHere()) {
            fragment = new MapsFragmentHere();
        } else {
            fragment = new MapsFragmentGoogle();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.map, fragment).commit();
    }

    /**
     * Validate that AndroidManifest.xml defines keys this application requires
     *
     * @return
     */
    private boolean validateMetaData() {
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            for (String key : REQUIRED_KEYS_ARRAY) {
                if (TextUtils.isEmpty(bundle.getString(key))) {
                    Log.d(TAG, "missing meta-data from AndroidManifest.xml. Undefined key " + key);
                    return false;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return true;
    }

    /**
     * Checks if HERE library (com.here.android) is available on the device
     *
     * @return true if HERE library is found
     */
    private boolean hasHere() {
        String[] systemSharedLibraryNames = getPackageManager().getSystemSharedLibraryNames();
        for (String library : systemSharedLibraryNames) {
            if (HERE_LIBRARY.equals(library))
                return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
