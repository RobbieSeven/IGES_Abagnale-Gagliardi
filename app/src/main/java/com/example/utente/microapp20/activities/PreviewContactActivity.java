package com.example.utente.microapp20.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utente.microapp20.Contact;
import com.example.utente.microapp20.ContactData;
import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;

import java.util.Iterator;

public class PreviewContactActivity extends MAActivity {

	private Contact con;
	
	@Override
	protected void initialize(Bundle savedInstanceState) {

	}

	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {
		ImageView im = (ImageView) findViewById(R.id.CallButton);
		TextView txnome = (TextView) findViewById(R.id.txNome);
		TextView txnumero = (TextView) findViewById(R.id.txNumero);
		TextView txmail = (TextView) findViewById(R.id.txMail);
		
		Bitmap bm = con.getImg();
		if(bm != null && (bm.getHeight() > 1 && bm.getWidth() > 1))
		    im.setImageBitmap(bm);
		
		txnome.setText(con.getName());
		String phones = "";
		for(String s : con.getPhones()) {
			phones = phones + s + "\n";
		}		
		txnumero.setText(phones);
		
		String mails = "";
		for(String s : con.getMails()) {
			mails = mails + s + "\n";
		}
		txmail.setText(mails);
		
		im.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				next();
				
			}
		});
	}	
	
	@Override
	protected void execute() {

	}	
	
	
	@Override
	public void initInputs() {
		Iterator<GenericData<?>> i=application.getData(mycomponent.getId(), DataType.CONTACT).iterator();
		 if (i.hasNext())
			con = (Contact) i.next().getSingleData();
	}

	@Override
	public void beforeNext() {
		ContactData c = new ContactData(mycomponent.getId(),con);
		application.putData(mycomponent, c);
	}
	
	@Override
	protected void resume(){
		//metodi per speech Vincenzo Savarese
	}

}
