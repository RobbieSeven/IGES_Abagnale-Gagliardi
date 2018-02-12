package com.giordanogiammaria.microapp30.component_fragment;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;


public class MapFragment extends ComponentFragment {
    View view;
    WebView webView;
    Location mLocation;

    @Override
    protected ComponentType setType() {
        return ComponentType.MAP;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputTypes=new HashMap<>();
        inputTypes.put("location",DataType.LOCATION);
        return inputTypes;

    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
        GenericData<Location> data =dataCollection.get("location");
        mLocation=data.getData().get(0);

    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        return new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.map, container, false);
        Button geog = view.findViewById(R.id.geographicButton);
        Button sat= view.findViewById(R.id.satButton);
        webView=view.findViewById(R.id.vewViewMaps);
        webView.setWebViewClient(new MyBrowser());
        geog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String url="https://www.google.it/maps/@"+mLocation.getLatitude()+","+mLocation.getLongitude()+",16z?hl=it";
               showMap(url);
           }
       });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sat="https://www.google.it/maps/@"+mLocation.getLatitude()+","+mLocation.getLongitude()+",1555m/data=!3m1!1e3?hl=it";
                showMap(sat);
            }
        });

        return view;
    }
    @SuppressLint("SetJavaScriptEnabled")
    void showMap(String url){
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webView.loadUrl(url);
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
