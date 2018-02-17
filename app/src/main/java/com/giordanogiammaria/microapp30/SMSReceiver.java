package com.giordanogiammaria.microapp30;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        StringBuilder str = new StringBuilder();
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            assert pdus != null;
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                str.append("SMS from ").append(msgs[i].getOriginatingAddress());
                str.append(" :");
                str.append(msgs[i].getMessageBody());
                str.append("n");
            }
            //---display the new SMS message---
            Toast.makeText(context, str.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}