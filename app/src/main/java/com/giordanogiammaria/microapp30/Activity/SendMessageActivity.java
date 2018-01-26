package com.giordanogiammaria.microapp30.Activity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.Facade.Facade;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public class SendMessageActivity extends ComponentFragment{
    View view;
    Facade facade;
    TextView contactName;
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
        view = inflater.inflate(R.layout.sendsms, container, false);
        // get the reference of Button
        String number="081940021";
        facade=new Facade(view.getContext());
        contactName=view.findViewById(R.id.tx_label_cont);
        contactName.setText(facade.getContactName(number,view.getContext()));
        sendSMS(number,"ciao mondo");
        return view;
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
