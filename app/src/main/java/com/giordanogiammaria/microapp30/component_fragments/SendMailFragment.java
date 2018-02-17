package com.giordanogiammaria.microapp30.component_fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.CodeDecode;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.manage_contact.Contact;
import java.util.ArrayList;
import java.util.HashMap;

public class SendMailFragment extends ComponentFragment{
    View view;
    Contact values;
    EditText subject,body;
    Button send;
    TextView to;
    ImageView image;
    Bitmap bitmap;
    Location loc;

    @Override
    protected ComponentType setType() {
        return ComponentType.SENDMAIL;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputType=new HashMap<>();
        inputType.put("contact", DataType.CONTACT);
        inputType.put("location",DataType.LOCATION);
        inputType.put("image",DataType.IMAGE);

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
        GenericData<Bitmap> bitmapGenericData=dataCollection.get("image");
        values=nameContact.getData().get(0);
        bitmap=bitmapGenericData.getData().get(0);
        loc=location.getData().get(0);
    }

    @Override
    public HashMap<DataType, GenericData> getOutputData() {
        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.mailpreview, container, false);
        subject=view.findViewById(R.id.subjectEditText);
        body= view.findViewById(R.id.bodyEditText);
        send=view.findViewById(R.id.sendButton);
        to=view.findViewById(R.id.toTextView);
        image=view.findViewById(R.id.imageViewContact);
        to.setText(String.format("%s %s", to.getText(), values.getEmailContact()));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {values.getEmailContact()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, body.getText().toString()+ " \nI am currently here:\nlat:"+loc.getLatitude()+" loc:"+loc.getLongitude());
                Uri uri= CodeDecode.getImageUri(view.getContext(),bitmap);
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(emailIntent, "Pick an email provider"));
            }
        });
        return view;
    }
}
