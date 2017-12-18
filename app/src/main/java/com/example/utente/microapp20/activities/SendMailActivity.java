package com.example.utente.microapp20.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utente.microapp20.ComplexDataType;
import com.example.utente.microapp20.Contact;
import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.GenericSaver;
import com.example.utente.microapp20.ImageData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.MAEntry;
import com.example.utente.microapp20.R;
import com.example.utente.microapp20.StringData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SendMailActivity extends MAActivity {
	private ArrayList<Uri> uris = new ArrayList<Uri>();
	private ArrayList<String> sendby = new ArrayList<String>();
	private String _subject = "";
	private String _body = "";
	private String _header = "";
	private boolean plain = true;

	@Override
	protected void initialize(Bundle savedInstanceState) {

	}

	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {
		TextView tx = (TextView) findViewById(R.id.emailaddress);
		TextView att = (TextView) findViewById(R.id.txallegati);
		Button btn = (Button) findViewById(R.id.SendButton);

		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				sendMail();
			}
		});

		EditText subject = (EditText) this.findViewById(R.id.mailsubject);
		subject.setText(_subject);


		for (String s : sendby) {
			tx.append(s + ";\n");
		}
		for (Uri u : uris) {
			att.append(u + "\n");
		}

		EditText body = (EditText) this.findViewById(R.id.mailbody);
		body.setText(_body);


	}	
	
	@Override
	protected void execute() {
		

	}	

	private void sendMail() {

		Intent emailIntent = new Intent(Intent.ACTION_SEND);

		if(sendby.size() == 0)
		{
			 AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 builder.setMessage("Invalid mail address")
			 .setTitle(R.string.error)			 
			 .setCancelable(false) 
			 .setPositiveButton(android.R.string.ok, null);
			 AlertDialog alert = builder.create();
			 alert.show();
			 return;
		}
		String[] stockArr = new String[sendby.size()];
		stockArr = sendby.toArray(stockArr);

		emailIntent.putExtra(Intent.EXTRA_EMAIL, stockArr);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, _subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, _body);

		if (plain)
			emailIntent.setType("text/plain");
		else
			emailIntent.setType("text/html");

		for (Uri u : uris) {
			emailIntent.putExtra(Intent.EXTRA_STREAM, u);
		}
		startActivityForResult(Intent.createChooser(emailIntent, "Send email..."), 0);
	}

	@Override
	public void initInputs() {

		String _innerString = "";
		// Gestisco pi input da diverse componenti ma questi input non sono
		// multipli

		// ricavo il subject e il body
		Iterable<GenericData<?>> dati = application.getData(mycomponent.getId(), DataType.STRING);
		if (dati != null) {
			Iterator<GenericData<?>> it = dati.iterator();

			List<MAEntry<String, String>> list = mycomponent.getInputComponents(DataType.STRING);

			while (it.hasNext()) {
				StringData d = (StringData) it.next();
				String id = d.getSourceId();

				for (MAEntry<String, String> s : list) {
					if (s.getKey().equals(id)) {
						if (s.getValue().equals("subject"))
							_subject = d.getSingleData();
						else if (s.getValue().equals("body"))
							_body += d.getSingleData();
						else
							_innerString = _innerString + d.getSingleData() +"\n"; 
					}
				}
			}
		}
		
		Iterable<GenericData<?>> i = application.getData(mycomponent.getId(), DataType.LOCATION);
		if (i != null) {
			for (GenericData<?> d : i) {
				 Location loc = (Location) d.getSingleData();
				  _header += " " + localityByLocation(loc.getLatitude(), loc.getLongitude()) + "\n";
			}
		}

		_header = _header + _innerString;
		
		// ricavo la mail dal contatto
		i = application.getData(mycomponent.getId(), DataType.CONTACT);

		if (i != null) {
			for (GenericData<?> d : i) {
				Contact con = (Contact) d.getSingleData();
				List<String> mails = con.getMails();

				for (String s : mails)
					sendby.add(s);
			}
		}
		
		// Allegati
		gestisciAllegati();
		
		if(!_header.equals(""))
			_body = _header + "\n\n"+ _body; 		

	}

	private void gestisciAllegati() {
		// TODO:gestisco eventuali Immagini
		Iterable<GenericData<?>> i = application.getData(mycomponent.getId(), DataType.IMAGE);
		addAttach(i);

		// TODO:gestisco objects:i complexData vengono salvati su un file xml o
		// html "da decidere quale formato" tramite la classe GenericSaver
		
		i = application.getData(mycomponent.getId(), DataType.OBJECT);
		addAttach(i);
	}
	
	private String localityByLocation(double geoLat, double geoLng) {
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		List<Address> addresses = null;
		try {
			addresses = geocoder.getFromLocation(geoLat, geoLng, 1);
		} catch (IOException e) {
		}

		return addresses.get(0).getLocality();
	}		
	
	private void addAttach(Iterable<GenericData<?>> i) {
		if (i != null) {
			for (GenericData<?> img : i) {
				File f = img.getFile();

				if (img instanceof ComplexDataType) {
					if (f == null) {
						// salvo come documento Html valore false
						// se si vuole salvare come xml valore true
						GenericSaver.saveObject(img, false);
						f = img.getFile();
					}
				}
				if (img instanceof ImageData) {
					if (f == null) {
						GenericSaver.saveBitmap(getContentResolver(), (ImageData)img);
						GenericSaver.saveObject(img, false);
						f = img.getFile();
					}
				}
				try {
					Uri v = Uri.fromFile(f);
					uris.add(v);
				} catch (Exception e) {
				}
			}
		}
	}

	public void beforeNext() {

	}

	@Override
	protected void resume(){
		//metodi per speech
	}

}
