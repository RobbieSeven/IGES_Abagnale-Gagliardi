package com.example.utente.microapp20;

import com.example.utente.microapp20.components.CallInterceptorComponent;
import com.example.utente.microapp20.components.CameraPreviewComponent;
import com.example.utente.microapp20.components.CameraTakeComponent;
import com.example.utente.microapp20.components.ContactsPreviewComponent;
import com.example.utente.microapp20.components.ContactsSelectComponent;
import com.example.utente.microapp20.components.MapsLocationComponent;
import com.example.utente.microapp20.components.MapsMapComponent;
import com.example.utente.microapp20.components.PhoneCallComponent;
import com.example.utente.microapp20.components.SaveSaveComponent;
import com.example.utente.microapp20.components.SendMailComponent;
import com.example.utente.microapp20.components.SendSMSComponent;
import com.example.utente.microapp20.exceptions.InvalidComponentException;
import com.example.utente.microapp20.exceptions.NoMoreSpaceException;

public class ConcreteComponentCreator implements ComponentCreator {

	public MAComponent createComponentService(String type, String id, String description) throws InvalidComponentException, NoMoreSpaceException {

		if (type.equals("CAMERA_TAKE"))
			return new CameraTakeComponent(id,description);
		if (type.equals("CAMERA_PREVIEW"))
			return new CameraPreviewComponent(id,description);
		if (type.equals("CONTACTS_SELECT"))
			return new ContactsSelectComponent(id,description);
		if (type.equals("CONTACTS_PREVIEW"))
			return new ContactsPreviewComponent(id,description);
		if (type.equals("MAPS_MAP"))
			return new MapsMapComponent(id,description);
		if (type.equals("MAPS_LOCATION"))
			return new MapsLocationComponent(id,description);
		if (type.equals("CALL_INTERCEPTOR"))
			return new CallInterceptorComponent(id,description);
		if (type.equals("PHONE_CALL"))
			return new PhoneCallComponent(id,description);
		if (type.equals("SAVE_SAVE"))
			return new SaveSaveComponent(id,description);
		if (type.equals("SEND_MAIL"))
			return new SendMailComponent(id,description);
		if (type.equals("SEND_TEXTSMS"))
			return new SendSMSComponent(id,description);
		throw new InvalidComponentException(type);
	}

}
