package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class SaveSaveComponent extends MAComponent {

	public SaveSaveComponent(String id, String description) {
		super(id, description);
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.SaveActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "SAVE_SAVE";
	}

}
