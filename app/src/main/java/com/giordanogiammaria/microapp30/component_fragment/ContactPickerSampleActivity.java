package com.giordanogiammaria.microapp30.component_fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.codinguser.android.contactpicker.ContactsPickerActivity;
import com.codinguser.android.contactpicker.OnContactSelectedListener;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;


public class ContactPickerSampleActivity extends ContactsPickerActivity implements OnContactSelectedListener {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null){
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }

        @Override
        public void onContactNumberSelected(String contactNumber, String contactName) {
           /* Toast.makeText(this, String.format("Selected:\n %s: %s\nAn intent would be delivered to your app",
                    contactName, contactNumber),
                    Toast.LENGTH_LONG).show();*/
            Intent intent=new Intent();
            intent.putExtra("number",contactNumber);
            intent.putExtra("name",contactName);

            setResult(1,intent);
            finish();//finishing activity
    }

    public void queryAllEmailAddressesFromContacts(int contactId) {
        final String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Email.ADDRESS,                         // use Email.ADDRESS for API-Level 11+
                ContactsContract.CommonDataKinds.Email.TYPE
        };

        final Cursor email = managedQuery(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                projection,
                ContactsContract.Data.CONTACT_ID + "=?",
                new String[]{String.valueOf(contactId)},
                null);

        if(email.moveToFirst()) {
            final int contactEmailColumnIndex = email.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
            final int contactTypeColumnIndex = email.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE);

            while(!email.isAfterLast()) {
                final String address = email.getString(contactEmailColumnIndex);
                final int type = email.getInt(contactTypeColumnIndex);
                email.moveToNext();
            }

        }
        email.close();
    }
}
