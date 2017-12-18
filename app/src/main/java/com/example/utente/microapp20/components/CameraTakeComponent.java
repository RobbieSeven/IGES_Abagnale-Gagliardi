package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class CameraTakeComponent extends MAComponent {

	public CameraTakeComponent(String id, String description) {
		super(id, description);
	}

	protected String getLocationQName() {
		return "it.unisa.microapp.activities.CameraTakeActivity";
	}

	protected String getCompType(String id) {
		return "CAMERA_TAKE";
	}

}
