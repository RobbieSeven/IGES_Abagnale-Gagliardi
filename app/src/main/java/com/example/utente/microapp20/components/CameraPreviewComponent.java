package com.example.utente.microapp20.components;

import com.example.utente.microapp20.MAComponent;

public class CameraPreviewComponent extends MAComponent {

	public CameraPreviewComponent(String id, String description) {
		super(id, description);
		
	}

	@Override
	protected String getLocationQName() {
		return "it.unisa.microapp.activities.PreviewActivity";
	}

	@Override
	protected String getCompType(String id) {
		return "CAMERA_PREVIEW";
	}

}
