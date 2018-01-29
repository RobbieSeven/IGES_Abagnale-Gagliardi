package com.giordanogiammaria.microapp30.Activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.Facade.Facade;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class    SendMessageActivity extends ComponentFragment{
    View view;
    Facade facade;
    TextView contactName;
    Button sendSmsButton;
    String number;
    EditText bodyMessage;
    TextView sendingText;
    ImageView picture;
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

        view = inflater.inflate(R.layout.sendsms, container, false);
        facade=new Facade(view.getContext());
        number="3342446869";
        sendSmsButton=view.findViewById(R.id.sendSms);
        contactName=view.findViewById(R.id.tx_label_cont);
        bodyMessage=view.findViewById(R.id.tx_container);
        sendingText=view.findViewById(R.id.sendingSmsTo);
        picture=view.findViewById(R.id.picture);
        contactName.setText(facade.getContactName(number,view.getContext()));
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bodyMessage.getText().toString().equalsIgnoreCase("")) {
                    sendSMS(number, bodyMessage.getText().toString());
                    changeLayout();
                }
                else Snackbar.make(view,"please enter text",Snackbar.LENGTH_LONG).show();

            }
        });
        Long contactId=getContactIDFromNumber(number,view.getContext());
        picture.setImageBitmap(getUserPhoto(contactId));
        return view;
    }

    private void changeLayout() {
        sendSmsButton.setVisibility(View.INVISIBLE);
        contactName.setText(R.string.messageSend);
        bodyMessage.setVisibility(View.INVISIBLE);
        sendingText.setVisibility(View.INVISIBLE);
        picture.setVisibility(View.INVISIBLE);
    }

    //questo metodo invia un messaggio dato un numero di telefono e un testo
    public void sendSMS(String phoneNo, String msg) {
        try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  long getContactIDFromNumber(String contactNumber, Context context) {
        String UriContactNumber = Uri.encode(contactNumber);
        long phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, UriContactNumber),
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        assert contactLookupCursor != null;
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getLong(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();
        return phoneContactID;
    }
    private Bitmap getUserPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                contactId);
        Uri displayPhotoUri = Uri.withAppendedPath(contactUri,
                ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
        try {
            AssetFileDescriptor fd = view.getContext().getContentResolver()
                    .openAssetFileDescriptor(displayPhotoUri, "r");
            assert fd != null;
            return Bitmap.createScaledBitmap(BitmapFactory.decodeStream(fd.createInputStream()), 250, 250, true);
        } catch (IOException e) {
            return null;
        }
    }

}

