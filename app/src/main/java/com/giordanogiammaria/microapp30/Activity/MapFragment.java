package com.giordanogiammaria.microapp30.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import java.util.ArrayList;
import java.util.HashMap;


public class MapFragment extends ComponentFragment {
    View view;
    WebView webView;

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

        view = inflater.inflate(R.layout.map, container, false);
        Button geog = view.findViewById(R.id.geographicButton);
        Button sat= view.findViewById(R.id.satButton);
        webView=view.findViewById(R.id.vewViewMaps);
        webView.setWebViewClient(new MyBrowser());
        geog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String url="https://www.google.it/maps/place/Via+di+Mezzo+Sud,+84012+Angri+" +
                       "SA/@40.7387186,14.5697312,17z/data=!3m1!4b1!4m5!" +
                       "3m4!1s0x133bbea419aa7911:0x63efb142ebaf5f9!8m2!3d40.7387186!4d14.5719199";
               showMap(url);
           }
       });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sat="https://www.google.it/maps/place/Via+Cervinia,+1442,+84012+Angri+SA/" +
                        "@40.7366266,14.5763493,3a,75y,245.75h,90t/data=!3m6!1e1!3m4!1sUIL4RRazogBv" +
                        "ZDpbUOp7Aw!2e0!7i13312!8i6656!4m5!3m4!1s0x133bbea35ccc5083:0xed9e02aa9eb" +
                        "45aa1!8m2!3d40.736478!4d14.575966";
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
