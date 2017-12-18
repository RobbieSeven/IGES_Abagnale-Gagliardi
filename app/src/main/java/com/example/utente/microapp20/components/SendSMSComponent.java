package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class SendSMSComponent extends MAComponent {

	public SendSMSComponent(String id, String description) {
		super(id, description);
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.SendSmsMessageActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "SEND_TEXTSMS";
	}

}
