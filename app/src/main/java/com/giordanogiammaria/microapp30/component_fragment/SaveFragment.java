package com.giordanogiammaria.microapp30.component_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;



public class SaveFragment extends ComponentFragment{
    View view;

    @Override
    protected ComponentType setType() {
        return null;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType>inputType=new HashMap<>();
        inputType.put("object",DataType.OBJECT);
        return inputType;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {

    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        return new HashMap<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.blank, container, false);
        // get the reference of Button
        return view;
    }
}
