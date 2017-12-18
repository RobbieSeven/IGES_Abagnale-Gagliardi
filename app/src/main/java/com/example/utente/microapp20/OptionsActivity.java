package com.example.utente.microapp20;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OptionsActivity extends Activity {

	private SharedPreferences prefs;
	
	EditText nickname;
	EditText email;
	EditText password;
	CheckBox debug;
	CheckBox useProvider;
	CheckBox speech;
	CheckBox sms;
	CheckBox whiteBackground;

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);

		setContentView(R.layout.options);
		
		prefs = getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE);
		
		nickname = (EditText) findViewById(R.id.nickname);
		
		email = (EditText) findViewById(R.id.email);
		email.setEnabled(false);
		password = (EditText) findViewById(R.id.password);
		password.setEnabled(false);
				
		useProvider = (CheckBox) findViewById(R.id.useProvider);
		useProvider.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean state) {
				email.setEnabled(state);
				password.setEnabled(state);	
			}
			
		});
		
		debug = (CheckBox) findViewById(R.id.debug);
		
		sms = (CheckBox) findViewById(R.id.sms);
		
		whiteBackground = (CheckBox) findViewById(R.id.wbackground);
		
		Button saveButton = (Button) findViewById(R.id.buttonSave);
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (check()) {
					savePreferences();
					finish();
				}
			}
		});

		Button resetButton = (Button) findViewById(R.id.buttonReset);
		resetButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				uploadPreferences();
			}
		});

		uploadPreferences();			
	}

	private boolean check() {
		if (nickname.getText().toString().equals("")) {
			Toast.makeText(this, "Nickname is mandatory", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(useProvider.isChecked())
		{
			if (email.getText().toString().equals("")) {
				Toast.makeText(this, "Email provider is mandatory", Toast.LENGTH_LONG).show();
				return false;
			}	
			if (password.getText().toString().equals("")) {
				Toast.makeText(this, "Password provider is mandatory", Toast.LENGTH_LONG).show();
				return false;
			}			
		} else
		{
			email.setText("");
			password.setText("");
		}

		return true;
	}

	private void savePreferences() {
		SharedPreferences.Editor editor = prefs.edit();

		editor.putString(Constants.TEXT_NICKNAME_KEY, nickname.getText().toString());

		editor.putString(Constants.TEXT_EMAILPROVIDER_KEY, email.getText().toString());

		editor.putString(Constants.TEXT_EMAILPASSWORD_KEY, password.getText().toString());

		editor.putBoolean(Constants.CB_EMAILPROVIDER_KEY, useProvider.isChecked());
		
		editor.putBoolean(Constants.CB_SPEECH_KEY, speech.isChecked());

		editor.putBoolean(Constants.CB_SMS_KEY, sms.isChecked());

		editor.putBoolean(Constants.CB_WB_KEY, whiteBackground.isChecked());

		editor.putBoolean(Constants.CB_DEBUG_KEY, debug.isChecked());
		Constants.__DEBUG = debug.isChecked();

		editor.commit();
	}

	private void uploadPreferences() {
		nickname.setText(prefs.getString(Constants.TEXT_NICKNAME_KEY, ""));

		email.setText(prefs.getString(Constants.TEXT_EMAILPROVIDER_KEY, ""));

		password.setText(prefs.getString(Constants.TEXT_EMAILPASSWORD_KEY, ""));

		useProvider.setChecked(prefs.getBoolean(Constants.CB_EMAILPROVIDER_KEY, false));

		debug.setChecked(prefs.getBoolean(Constants.CB_DEBUG_KEY, false));
		Constants.__DEBUG = debug.isChecked();
		
		speech.setChecked(prefs.getBoolean(Constants.CB_SPEECH_KEY, false));

		sms.setChecked(prefs.getBoolean(Constants.CB_SMS_KEY, false));

		whiteBackground.setChecked(prefs.getBoolean(Constants.CB_WB_KEY, false));
		
	}
	
	public static boolean getBoolean(String key, Context con) {
		return con.getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE).getBoolean(key, false);
	}
	
	public static String getString(String key, Context con) {
		return con.getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE).getString(key, "");
	}	
	
	public static boolean isFirstRun(Context con) {
		boolean fr = con.getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE).getBoolean("FIRST_RUN", true);
		if(fr) {
			SharedPreferences.Editor editor = con.getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE).edit();
			editor.putBoolean("FIRST_RUN", false);
			
			editor.putBoolean(Constants.CB_DEBUG_KEY, true);
			Constants.__DEBUG = true;
			
			editor.commit();
		}
		return fr;
	}	
	
	public static void removePreferences(Context con) {
			SharedPreferences.Editor editor = con.getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE).edit();
			editor.clear();
			editor.commit();
	}		

}
