package com.giordanogiammaria.microapp30.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public class SendMailActivity extends ComponentFragment{
    View view;
    EditText username,pass;
    Button cancel,login;

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
        view = inflater.inflate(R.layout.mailpreview, container, false);
        // get the reference of Button
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"giammagiorda@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
      /*File root = Environment.getExternalStorageDirectory();
        String pathToMyAttachedFile = "drawable://" + R.drawable.email300x300;
        File file = new File(root, pathToMyAttachedFile);
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        Uri uri = Uri.fromFile(file);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);*/
        startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));

        return view;
    }
}
