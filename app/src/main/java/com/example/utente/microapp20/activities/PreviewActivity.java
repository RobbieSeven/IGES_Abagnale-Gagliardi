package com.example.utente.microapp20.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utente.microapp20.DataType;
import com.example.utente.microapp20.GenericData;
import com.example.utente.microapp20.ImageData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;

import java.util.Iterator;

public class PreviewActivity extends MAActivity {

	private Bitmap bm;
	
	@Override
	public void initialize(Bundle savedInstanceState) {
		bm = null;
	}
	
	@Override
	public void prepare() {		
	}
	
	@Override
	public void prepareView(View v) {
		if(bm != null) {
			ImageView im=(ImageView)this.findViewById(R.id.pre_img);
			//im.setImageBitmap(bm);
			im.setImageBitmap(Bitmap.createScaledBitmap(bm, 300, 350, false));
		}
		TextView tw=(TextView) this.findViewById(R.id.pre_text);
		tw.setTextSize(18);
	}
	
	@Override
	protected void execute() {
		
	}	
	
	public void initInputs() {
		Iterator<GenericData<?>> i=application.getData(mycomponent.getId(), DataType.IMAGE).iterator();
		if (i!=null && i.hasNext())
			bm=(Bitmap) i.next().getSingleData(); //Voglio recuperare un elemento da un solo input
	}

	@Override
	public void beforeNext() {
		ImageData image = new ImageData(mycomponent.getId(),bm);
		application.putData(mycomponent, image);
	}

	
	public void onHidden() { 
		next();
	}

	@Override
	protected void resume(){
		//metodi per speech
	}

}
