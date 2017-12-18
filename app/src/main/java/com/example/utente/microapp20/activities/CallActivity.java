package com.example.utente.microapp20.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;

import com.example.utente.microapp20.Contact;
import com.example.utente.microapp20.ContactData;
import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.MAActivity;

import java.util.Iterator;

public class CallActivity extends MAActivity {
	private Contact con;
	private String callUri;
	private String text = "";
	private boolean ended, controlpause = false;

	@SuppressWarnings("unused")
	private class EndCallListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// wait for phone to go offhook (probably set a boolean flag) so
				// you know your app initiated the call.
				text += "\nRing";
			}
			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// wait for phone to go offhook (probably set a boolean flag) so
				// you know your app initiated the call.
				// text+="\nHookoff";
				// hook=true;
			}
			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// text+="\nIdle";

				// if (hook)
				// ended=true;
			}

		}
	}

	@Override
	public void initialize(Bundle savedInstanceState) {

	}

	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {

	}

	@Override
	protected void execute() {
		if (callUri != null) {
			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(callUri));
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			startActivity(callIntent);
			controlpause = true;
		}
	}
	
	@Override
	public void resume() {
		if (ended && controlpause) {
			next();
			return;
		}
	}

	@Override
	public void initInputs() {
		Iterator<GenericData<?>> i=application.getData(mycomponent.getId(), DataType.CONTACT).iterator();
		if (i.hasNext())
			con = (Contact) i.next().getSingleData();
		callUri = "tel:" + con.getPhone();

	}

	@Override
	public void beforeNext() {
		ContactData c=new ContactData(mycomponent.getId(),con);
		application.putData(mycomponent, c);
	}

	@Override
	public void pause() {
		ended = true;
	}

}
