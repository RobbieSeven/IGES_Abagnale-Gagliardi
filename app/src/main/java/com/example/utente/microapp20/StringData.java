package com.example.utente.microapp20;

import java.util.Collection;


public class StringData extends GenericData<String>{

		
	public StringData(String sourceId, Collection<String> data) {
		super(sourceId, data);
	}

	public StringData(String sourceId, String data) {
		super(sourceId, data);
	}

	@Override
	public DataType getDataType() {
		return DataType.STRING;
	}
	
}
