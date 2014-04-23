/*
 * Copyright (c) 2014 Nokia Corporation and/or its subsidiary(-ies).
 * See the license text file delivered with this project for more information.
 */

package com.nokia.example.mapsv2oneapk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nokia.android.gms.maps.GoogleMap;
import com.nokia.android.gms.maps.SupportMapFragment;
import com.nokia.android.gms.maps.model.LatLng;
import com.nokia.android.gms.maps.model.MarkerOptions;

/**
 * HERE version of fragment that draws the map. This fragment is instantiated in the
 * {@link com.nokia.example.mapsv2oneapk.MainActivity} and inserted to the layout if HERE libraries are available.
 * This file is identical to {@link com.nokia.example.mapsv2oneapk.MapsFragmentGoogle} except for import statements
 * and Resource ID's used
 */
public class MapsFragmentHere extends Fragment {

    /**
     * Resource ID to HERE version of layout
     */
    public static final int FRAGMENT_LAYOUT = R.layout.fragment_map_here;

    /**
     * Resource ID of the {@link com.nokia.android.gms.maps.SupportMapFragment}
     */
    public static final int FRAGMENT_ID = R.id.fragment_map_here;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(FRAGMENT_LAYOUT, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(FRAGMENT_ID);
        GoogleMap map = mapFragment.getMap();
        MarkerOptions marker = new MarkerOptions().position(new LatLng(0, 0)).title("Marker");
        map.addMarker(marker);

        // Get tracker.
        Tracker t = ((MapsApplication) getActivity().getApplication()).getTracker(
                MapsApplication.TrackerName.APP_TRACKER);

        // Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName(MapsFragmentHere.class.getName());

        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
    }
}
