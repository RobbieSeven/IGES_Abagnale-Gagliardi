package com.giordanogiammaria.microapp30.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


public class LocationFragment extends ComponentFragment{
    View view;
    TextView lat,lon;
    LocationManager mLocationManager;
    Location myLocation ;
    @Override
    public HashMap<String, DataType> getInputTypes() {
        return null;
    }

    @Override
    public ArrayList<DataType> getOutputTypes() {
        return null;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {

    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.location_layout, container, false);
        lat=view.findViewById(R.id.lat);
        lon=view.findViewById(R.id.lon);
        /*LocationManager lm = (LocationManager)view.getContext().getSystemService(Context.LOCATION_SERVICE);
        Log.d("lm:",lm.toString());
        assert lm != null;
        @SuppressLint("MissingPermission")
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        lat.setText(String.format("%s%s", lat.getText(),latitude));
        lon.setText(String.format("%s%s",lon.getText(),longitude));
        // get the reference of Button*/
        myLocation = getLastKnownLocation();
        lat.setText(String.format("%s%s", lat.getText(), myLocation.getLatitude()));
        lon.setText(String.format("%s%s", lon.getText(), myLocation.getLongitude()));
        return view;
    }
    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager)view.getContext().getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }
}
