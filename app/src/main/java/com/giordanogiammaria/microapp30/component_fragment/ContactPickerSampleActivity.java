package com.giordanogiammaria.microapp30.component_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.codinguser.android.contactpicker.ContactsPickerActivity;
import com.codinguser.android.contactpicker.OnContactSelectedListener;

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
            Intent intent=new Intent();
            intent.putExtra("number",contactNumber);
            intent.putExtra("name",contactName);

            setResult(1,intent);
            finish();//finishing activity
    }
}
