package com.example.utente.microapp20;

import java.util.Collection;

public class ContactData extends GenericData<Contact> {

	public ContactData(String sourceId, Collection<Contact> data) {
		super(sourceId, data);
	}

	public ContactData(String sourceId, Contact data) {
		super(sourceId, data);
	}

	@Override
	public DataType getDataType() {

		return DataType.CONTACT;
	}

}
