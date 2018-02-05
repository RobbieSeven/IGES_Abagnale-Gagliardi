package com.giordanogiammaria.microapp30.component_fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giordanogiammaria.microapp30.Component;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;



public class SendMailFragment extends ComponentFragment{
    View view;
    Contact values;

    @Override
    protected ComponentType setType() {
        return ComponentType.SENDMAIL;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputType=new HashMap<>();
        inputType.put("contact", DataType.CONTACT);
        inputType.put("location",DataType.LOCATION);

        return inputType;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
        GenericData<Location> location= dataCollection.get("location");
        GenericData<Contact> nameContact=dataCollection.get("contact");
        values=nameContact.getData().get(0);


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
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {values.getEmailContact()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
      /*File root = Environment.getExternalStorageDirectory();
        String pathToMyAttachedFile = "drawable://" + R.drawable.email300x300;
        File file = new File(root, pathToMyAttachedFile);
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        Uri uri = Uri.fromFile(file);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);*/
        startActivity(Intent.createChooser(emailIntent, "Pick an email provider"));

        return view;
    }
}
