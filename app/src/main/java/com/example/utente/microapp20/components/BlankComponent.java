package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class BlankComponent extends MAComponent {

	// Solo per questo componente fittizio
	String _id;

	public BlankComponent(String id) {
		super(id, "");
		this._id = id;
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.BlankActivity";
	}

	@Override
	protected String getCompType(String id) {
		if(this._id == null) return id;
		return this._id;
	}

}
