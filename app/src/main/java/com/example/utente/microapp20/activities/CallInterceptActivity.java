package com.example.utente.microapp20.activities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;

import com.example.utente.microapp20.Contact;
import com.example.utente.microapp20.ContactData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;

import java.io.InputStream;

public class CallInterceptActivity extends MAActivity {

	private CallListener callListener; 
	private TelephonyManager mTM;

	@Override
	protected void initialize(Bundle savedInstanceState) {
		callListener = new CallListener();
	}
	
	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {

	}	
	
	@Override
	protected void execute() {

	}	
	
	class CallListener extends PhoneStateListener {
		public void onCallStateChanged(int state, String incomingNumber) {
			if (TelephonyManager.CALL_STATE_RINGING == state) {
				createContactData(incomingNumber);
			}
			if (TelephonyManager.CALL_STATE_IDLE == state) {

			}
		}
	}

	@Override
	public void initInputs() {
		mTM = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
	}

	private void createContactData(String phoneNo) {
		Contact c = retrieveData(phoneNo);
		ContactData data = new ContactData(mycomponent.getId(), c);
		application.putData(mycomponent, data);
		next();
	}

	private Contact retrieveData(String phoneNo) {
		//boolean found = false;
		Contact myContact = new Contact();
		ContentResolver cr = getContentResolver();
		myContact.setPhone(phoneNo);

		if (phoneNo != "") {
			Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNo));
			Cursor cursor = cr.query(uri, null, null, null, null);
			if (cursor.moveToFirst()) {
				//found = true;
				// new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup._ID}
				long photoId = cursor.getLong(cursor.getColumnIndex(PhoneLookup.PHOTO_ID));
				String name = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));
				long id = cursor.getLong(cursor.getColumnIndex(PhoneLookup._ID));
				Bitmap b;
				if (photoId == 0) {
					b = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.robot));
				} else {
					Uri cntactPhotoUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
					InputStream photo = Contacts.openContactPhotoInputStream(getContentResolver(),
							cntactPhotoUri);
					b = BitmapFactory.decodeStream(photo);
				}
				myContact.setImg(b);
				myContact.setName(name);

			}
			cursor.close();
		}
		else {
			myContact.setName("Unknown contact");
			Bitmap b = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.robot));
			myContact.setImg(b);
			myContact.setPhone(phoneNo);
		}
		return myContact;
	}

	@Override
	protected void onCondition(boolean state) {

	}	
		
	@Override
	protected void beforeNext() {
		mTM.listen(callListener, PhoneStateListener.LISTEN_NONE);
	}

	@Override
	protected void destroy() {
		mTM.listen(callListener, PhoneStateListener.LISTEN_NONE);
	}	
	
	@Override
	protected void resume() {}

}
