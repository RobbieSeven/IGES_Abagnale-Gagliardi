package com.example.utente.microapp20.activities;

import android.os.Bundle;
import android.view.View;

import com.example.utente.microapp20.ContactData;
import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.GenericSaver;
import com.example.utente.microapp20.ImageData;
import com.example.utente.microapp20.LocationData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.StringData;

public class SaveActivity extends MAActivity {

	@Override
	protected void initialize(Bundle savedInstanceState) {

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

	@Override
	public void initInputs() {

		Iterable<GenericData<?>> it = application.getData(mycomponent.getId(), DataType.LOCATION);
		if (it != null)
		{	
			for (GenericData<?> d : it) {
				LocationData loc = (LocationData) d;
				GenericSaver.saveLocation(loc);
				application.putData(mycomponent, loc);
			}
		}
		it = application.getData(mycomponent.getId(), DataType.STRING);
		if (it != null)
			for (GenericData<?> d : it) {
				StringData st = (StringData) d;

				GenericSaver.saveString(st);
				application.putData(mycomponent, st);

			}

		it = application.getData(mycomponent.getId(), DataType.IMAGE);
		if (it != null)
			for (GenericData<?> d : it) {
				ImageData st = (ImageData) d;

				GenericSaver.saveBitmap(getContentResolver(), st);
				application.putData(mycomponent, st);
			}
	
		it = application.getData(mycomponent.getId(), DataType.CONTACT);
		if (it != null)
			for (GenericData<?> d : it) {
				ContactData st = (ContactData) d;

				GenericSaver.saveContact(st);
				application.putData(mycomponent, st);

			}
		
		it = application.getData(mycomponent.getId(), DataType.OBJECT);
		if (it != null)
			for (GenericData<?> d : it) {
				GenericSaver.saveObject(d,true);
				application.putData(mycomponent,d);
			}

	}

	@Override
	public void beforeNext() {
	}
	
	@Override
	protected void resume(){
		//metodi per speech Vincenzo Savarese
	}

}
