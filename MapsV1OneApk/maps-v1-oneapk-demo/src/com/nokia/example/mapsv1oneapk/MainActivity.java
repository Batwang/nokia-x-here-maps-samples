/*
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.nokia.example.mapsv1oneapk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    private static final String HERE_LIBRARY = "com.here.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Replace MainActivity with {@link MapViewActivityHere} or {@link MapViewActivityGoogle}
        Intent intent;
        if (hasHere()) {
            intent = new Intent(this, MapViewActivityHere.class);
        } else {
            intent = new Intent(this, MapViewActivityGoogle.class);
        }
        startActivity(intent);
        finish();
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