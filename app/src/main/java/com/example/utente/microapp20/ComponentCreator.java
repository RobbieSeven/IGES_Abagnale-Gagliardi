package com.example.utente.microapp20;

import com.example.utente.microapp20.exceptions.InvalidComponentException;
import com.example.utente.microapp20.exceptions.NoMoreSpaceException;

public interface ComponentCreator {

	MAComponent createComponentService(String type, String id, String description) throws InvalidComponentException, NoMoreSpaceException;

}
