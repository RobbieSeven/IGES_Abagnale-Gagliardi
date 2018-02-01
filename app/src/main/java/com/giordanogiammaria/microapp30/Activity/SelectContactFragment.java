package com.giordanogiammaria.microapp30.Activity;

import android.content.Intent;
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


public class SelectContactFragment extends ComponentFragment{
    View view;
    String name,contact;
    TextView nameTextView,contactTextView;
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
        view = inflater.inflate(R.layout.contactpreview, container, false);
        nameTextView=view.findViewById(R.id.txNome);
        contactTextView= view.findViewById(R.id.txNumero);
        Intent intent= new Intent(view.getContext(),ContactPickerSampleActivity.class);
        startActivityForResult(intent,1);
        nameTextView.setText(name);
        contactTextView.setText(contact);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent databack) {
            if (requestCode==1){
                name=databack.getStringExtra("name");
                Log.d("number",databack.getStringExtra("number"));
                contact=databack.getStringExtra("number");
                nameTextView.setText(name);
                contactTextView.setText(contact);
            }
    }
}

