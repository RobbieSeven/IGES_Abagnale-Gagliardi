package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class SendMailComponent extends MAComponent {

	public SendMailComponent(String id, String description) {
		super(id, description);
	}
	
	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.SendMailActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "SEND_MAIL";
	}

}
