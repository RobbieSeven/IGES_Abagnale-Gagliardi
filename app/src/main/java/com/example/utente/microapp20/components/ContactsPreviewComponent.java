package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class ContactsPreviewComponent extends MAComponent {

	public ContactsPreviewComponent(String id, String description) {
		super(id, description);
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.PreviewContactActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "CONTACTS_PREVIEW";
	}

}
