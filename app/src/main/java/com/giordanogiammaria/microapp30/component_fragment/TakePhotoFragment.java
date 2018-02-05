package com.giordanogiammaria.microapp30.component_fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;


public class TakePhotoFragment extends ComponentFragment {
    View view;
    Bitmap image;

    @Override
    protected ComponentType setType() {
        return ComponentType.TAKEPHOTO;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        return null;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType> outputTypes=new ArrayList<>();
        outputTypes.add(DataType.IMAGE);
        return outputTypes;
    }

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
        HashMap<DataType,GenericData> outputData=new HashMap<>();
        GenericData<Bitmap> data=new GenericData<>();
        data.addData(image);
        outputData.put(DataType.IMAGE,data);
        return outputData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.prewiew, container, false);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1);
        return view;
    }
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            image = (Bitmap) data.getExtras().get("data");
            ImageView imageview =  view.findViewById(R.id.pre_img);
            imageview.setImageBitmap(image);

        }
    }
}