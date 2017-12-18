package com.example.utente.microapp20;

import android.location.Location;

import java.util.Collection;

public class LocationData extends GenericData<Location> {

	public LocationData(String sourceId, Collection<Location> data) {
		super(sourceId, data);
	}

	public LocationData(String sourceId, Location data) {
		super(sourceId, data);
	}

	@Override
	public DataType getDataType() {
		return DataType.LOCATION;
	}

}
