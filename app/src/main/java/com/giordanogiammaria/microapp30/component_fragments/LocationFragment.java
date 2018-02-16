package com.giordanogiammaria.microapp30.component_fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.giordanogiammaria.microapp30.ListFile;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
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
    protected ComponentType setType() {
        return ComponentType.LOCATION;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        return new HashMap<>();
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType> outputTypes=new ArrayList<>();
        outputTypes.add(DataType.LOCATION);
        return outputTypes;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {

    }

    @Override
    public HashMap<DataType, GenericData> getOutputData() {
        HashMap<DataType,GenericData> outputData=new HashMap<>();
        GenericData<Location> data= new GenericData<>();
        data.addData(myLocation);
        outputData.put(DataType.LOCATION,data);
        return outputData;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.location_layout, container, false);
        lat=view.findViewById(R.id.lat);
        lon=view.findViewById(R.id.lon);
        myLocation = getLastKnownLocation();

            myLocation = getLastKnownLocation();
            if (myLocation != null) {
                lat.setText(String.format("%s%s", lat.getText(), myLocation.getLatitude()));
                lon.setText(String.format("%s%s", lon.getText(), myLocation.getLongitude()));
            }else{
                Toast.makeText(view.getContext(),"please active the GPS",Toast.LENGTH_LONG).show();
                startActivity(new Intent(view.getContext(), ListFile.class));
            }

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
