package com.giordanogiammaria.microapp30.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public class TakePhotoActivity extends ComponentFragment{
    View view;
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
        view = inflater.inflate(R.layout.take, container, false);
        // get the reference of Button
        return view;
    }
}
