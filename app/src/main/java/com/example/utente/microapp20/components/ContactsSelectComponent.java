package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class ContactsSelectComponent extends MAComponent {

	public ContactsSelectComponent(String id, String description) {
		super(id, description);
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.SelectContactActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "CONTACTS_SELECT";
	}

}
