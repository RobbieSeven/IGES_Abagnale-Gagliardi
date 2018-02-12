package com.giordanogiammaria.microapp30.component_fragment;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.facade.Facade;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SendMessageFragment extends ComponentFragment{
    View view;
    Facade facade;
    TextView contactName;
    CircularProgressButton sendSmsButton;
    String number;
    EditText bodyMessage;
    TextView sendingText;
    ImageView picture;
    Contact values;
    @Override
    protected ComponentType setType() {
        return ComponentType.SENDMESSAGE;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputType=new HashMap<>();
        inputType.put("contact",DataType.CONTACT);
        return inputType;

    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
        GenericData<Contact> data = dataCollection.get("contact");
        values=data.getData().get(0);
    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        return new HashMap<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sendsms, container, false);
        facade=new Facade(view.getContext());
        number=values.getNumberContact();
        sendSmsButton= view.findViewById(R.id.sendSms);
        sendSmsButton.setIndeterminateProgressMode(true);

        contactName=view.findViewById(R.id.tx_label_cont);
        bodyMessage=view.findViewById(R.id.tx_container);
        sendingText=view.findViewById(R.id.sendingSmsTo);
        picture=view.findViewById(R.id.picture);
        contactName.setText(facade.getContactName(number,view.getContext()));
        //sendSmsButton.setBackgroundColor(Color.GRAY);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bodyMessage.getText().toString().equalsIgnoreCase("")) {
                    if (sendSmsButton.getProgress() == 0) {
                        sendSmsButton.setProgress(100);
                        sendSMS(number, bodyMessage.getText().toString());
                        changeLayout();
                    }
                }
                else Snackbar.make(view,"please enter text",Snackbar.LENGTH_LONG).show();

                }
            });
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            String data = prefs.getString("imageContact", "no id");
        if (data.equalsIgnoreCase("no id")){
                picture.setImageDrawable(getResources().getDrawable(R.drawable.ic_contact_picture));
            }
        else
                picture.setImageBitmap(BitmapFactory.decodeFile(data));
        return view;
        }

    private void changeLayout() {
        sendSmsButton.setVisibility(View.VISIBLE);
        sendSmsButton.setEnabled(false);
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
}

