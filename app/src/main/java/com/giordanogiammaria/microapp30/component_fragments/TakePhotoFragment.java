package com.giordanogiammaria.microapp30.component_fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.io.File;
import java.io.FileOutputStream;
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
        return new HashMap<>();
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType> outputTypes=new ArrayList<>();
        outputTypes.add(DataType.IMAGE);
        return outputTypes;
    }

    @Override
    public HashMap<String, DataType> getInputTypes() {
        return new HashMap<>();
    }
    @Override
    public ArrayList<DataType> getOutputTypes() {
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
    }

    @Override
    public HashMap<DataType, GenericData> getOutputData() {
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
            String name=saveImage(image,"fname");
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("fname", name);
            Log.d("fname:",name);
            editor.apply();
        }
    }
    private String saveImage(Bitmap finalBitmap, String image_name) {
       String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fname;
    }
}
