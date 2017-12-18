package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class MapsMapComponent extends MAComponent {

	public MapsMapComponent(String id, String description) {
		super(id, description);
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.MapsActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "MAPS_MAP";
	}

}
