package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class PhoneCallComponent extends MAComponent {

	public PhoneCallComponent(String id, String description) {
		super(id, description);
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.CallActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "PHONE_CALL";
	}

}
