package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class CallInterceptorComponent extends MAComponent {

	public CallInterceptorComponent(String id, String description) {
		super(id, description);	
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.CallInterceptActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "CALL_INTERCEPTOR";
	}

}
