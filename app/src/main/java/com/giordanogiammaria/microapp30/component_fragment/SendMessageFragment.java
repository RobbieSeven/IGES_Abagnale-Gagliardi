package com.giordanogiammaria.microapp30.component_fragment;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.facade.Facade;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.giordanogiammaria.microapp30.CodeDecode.decodeBase64;

public class SendMessageFragment extends ComponentFragment {
    View view;
    Facade facade;
    TextView contactName;
    CircularProgressButton sendSmsButton;
    String number;
    EditText bodyMessage;
    TextView sendingText;
    ImageView picture;
    Contact values;
    Context context;

    @Override
    protected ComponentType setType() {
        return ComponentType.SENDMESSAGE;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String, DataType> inputType = new HashMap<>();
        inputType.put("contact", DataType.CONTACT);
        return inputType;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
        GenericData<Contact> data = dataCollection.get("contact");
        values = data.getData().get(0);
    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        return new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sendsms, container, false);
        facade = new Facade(view.getContext());
        number = values.getNumberContact();
        sendSmsButton = view.findViewById(R.id.sendSms);
        sendSmsButton.setIndeterminateProgressMode(true);
        contactName = view.findViewById(R.id.tx_label_cont);
        bodyMessage = view.findViewById(R.id.tx_container);
        sendingText = view.findViewById(R.id.sendingSmsTo);
        picture = view.findViewById(R.id.picture);
        context = container.getContext();
        contactName.setText(facade.getContactName(number, context));
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bodyMessage.getText().toString().equalsIgnoreCase("")) {

                    if (sendSmsButton.getProgress() == 0) {
                        sendSmsButton.setProgress(100);
                        sendSMS(number, bodyMessage.getText().toString());
                        changeLayout();
                    }
                } else Snackbar.make(view, "please enter text", Snackbar.LENGTH_LONG).show();
            }
        });
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String data = prefs.getString("imagePreference", "no id");
        picture.setImageBitmap(decodeBase64(data));
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
        if (isSimExists()) {
            sendMySMS(phoneNo,msg);
        }else
            Snackbar.make(view,"sim not found",Snackbar.LENGTH_LONG).show();
    }

    private void sendMySMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
        PendingIntent sentPI = PendingIntent.getBroadcast(view.getContext(), 0,
                new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(view.getContext(), 0,
                new Intent(DELIVERED), 0);
        view.getContext().registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(view.getContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        sendSmsButton.setVisibility(View.INVISIBLE);
                        sendSmsButton.setVisibility(View.VISIBLE);
                        sendSmsButton.setBackgroundColor(Color.GREEN);
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(view.getContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        contactName.setText(R.string.generic_failure);
                        sendSmsButton.setVisibility(View.INVISIBLE);
                        sendSmsButton.setVisibility(View.VISIBLE);
                        sendSmsButton.setBackgroundColor(Color.RED);
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(view.getContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        contactName.setText(R.string.no_service);
                        sendSmsButton.setVisibility(View.INVISIBLE);
                        sendSmsButton.setVisibility(View.VISIBLE);
                        sendSmsButton.setBackgroundColor(Color.RED);
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(view.getContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        contactName.setText(R.string.null_pdu);
                        sendSmsButton.setVisibility(View.INVISIBLE);
                        sendSmsButton.setVisibility(View.VISIBLE);
                        sendSmsButton.setBackgroundColor(Color.RED);
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(view.getContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        contactName.setText(R.string.radio_off);
                        sendSmsButton.setVisibility(View.INVISIBLE);
                        sendSmsButton.setVisibility(View.VISIBLE);
                        sendSmsButton.setBackgroundColor(Color.RED);
                        break;
                }
            }
        }, new IntentFilter(SENT));
        //---when the SMS has been delivered---
        view.getContext().registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(view.getContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(view.getContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        sendSmsButton.setBackgroundColor(Color.RED);
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

    public boolean isSimExists() {
        TelephonyManager telephonyManager = (TelephonyManager) view.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        assert telephonyManager != null;
        int SIM_STATE = telephonyManager.getSimState();
        return SIM_STATE == TelephonyManager.SIM_STATE_READY;
    }
}
