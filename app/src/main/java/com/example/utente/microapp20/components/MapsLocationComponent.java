package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class MapsLocationComponent extends MAComponent {

	public MapsLocationComponent(String id, String description) {
		super(id, description);
	}


	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.MapsLocationActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "MAPS_LOCATION";
	}

}
