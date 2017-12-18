package com.example.utente.microapp20;

import android.graphics.Bitmap;

import java.util.Collection;

public class ImageData extends GenericData<Bitmap>{

	public ImageData(String sourceId, Bitmap data) {
		super(sourceId, data);
		
	}

	public ImageData(String sourceId, Collection<Bitmap> data) {
		super(sourceId, data);
	}
	
	@Override
	public DataType getDataType() {
		return DataType.IMAGE;
	}
	
}
