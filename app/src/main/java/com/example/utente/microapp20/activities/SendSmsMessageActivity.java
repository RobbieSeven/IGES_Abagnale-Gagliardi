package com.example.utente.microapp20.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utente.microapp20.Constants;
import com.example.utente.microapp20.Contact;
import com.example.utente.microapp20.ContactData;
import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.OptionsActivity;
import com.example.utente.microapp20.R;
import com.example.utente.microapp20.StringData;

import java.util.Iterator;

public class SendSmsMessageActivity extends MAActivity {
	private Contact con;
	private String message ="";

	@Override
	protected void initialize(Bundle savedInstanceState) {
	}

	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {
		TextView tx1 = (TextView) findViewById(R.id.tx_label_cont);
		TextView tx2 = (TextView) findViewById(R.id.tx_label_cont2);
		ImageView iv = (ImageView) findViewById(R.id.picture);
		
		EditText et = (EditText) findViewById(R.id.tx_container);
		et.setKeyListener(null);

		Bitmap bm = con.getImg();
		if (bm != null) {
			iv.setImageBitmap(bm);
		}
		
		String s = con.getName();
		if (s != null) {
			tx1.setText(s);
		} else
			tx1.setText("");

		s = con.getPhone();
		if (s != null) {
			tx2.setText(s);
		} else
			tx2.setText("");

		et.setText(message);
	}	
	
	@Override
	protected void execute() {

	}	

	@Override
	public void initInputs() {
		Iterator<GenericData<?>> i = application.getData(mycomponent.getId(), DataType.CONTACT).iterator();
		if (i.hasNext())
			con = (Contact) i.next().getSingleData();

		//Iterator<GenericData<?>> im = application.getData(mycomponent.getId(), DataType.STRING).iterator();
		//while (im.hasNext())
		//	message = message.concat(" " + (String) im.next().getSingleData());
		// message= mycomponent.getUserData("message").iterator().next();
		// recupero messaggio da inviare da XML <userinput name="Message"/>
		
		Iterable<GenericData<?>> it = application.getData(mycomponent.getId(), DataType.STRING);
		if (it != null)
			for (GenericData<?> d : it) {
				StringData st = (StringData) d;
				for (String s : st.getData()) {
					message = message.concat(" " + s);
				}				
			}		
		
	}

	protected String getNextLabel() {
		return getString(R.string.send);
	}

	private void sendSMS() {
		String phoneNo = con.getPhone();

		if (phoneNo != null) {
			try {
				SmsManager smsManager = SmsManager.getDefault();
				if(!OptionsActivity.getBoolean(Constants.CB_SMS_KEY, this))
					smsManager.sendTextMessage(phoneNo, null, message, null,null);
				
				Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				
				Toast.makeText(getApplicationContext(), "SMS failed!", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void beforeNext() {

		sendSMS();

		ContactData c = new ContactData(mycomponent.getId(), con);
		application.putData(mycomponent, c);

	}
	
	@Override
	protected void resume(){
		//metodi per speech Vincenzo Savarese
	}
	
}
