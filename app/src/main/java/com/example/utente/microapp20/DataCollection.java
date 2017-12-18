package com.example.utente.microapp20;

import java.util.HashMap;
import java.util.Map;

public class DataCollection {
	private Map<DataType, DataContainer> dataMap=new HashMap<DataType, DataContainer>();
	
	public void addData(GenericData<?> data){
		DataType type=data.getDataType();
		DataContainer cont=dataMap.get(type);
		if (cont==null)	
			dataMap.put(type, new DataContainer(data));
		else
			cont.addData(data);
	}
	
	public Iterable<GenericData<?>> getData (DataType dt){
		return dataMap.get(dt);
	}
			
	@Override
	public String toString() {
		return "DataCollection [dataMap=" + dataMap + "]";
	}
	
}
