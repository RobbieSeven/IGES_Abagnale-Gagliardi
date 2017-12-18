package com.example.utente.microapp20;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public abstract class GenericData <T> {

	protected ArrayList<T> dataCollection = new ArrayList<T>();
	protected String sourceId;
	private File f;
	
	public abstract  DataType getDataType();
	
	public GenericData(String sourceId, T data)
	{
		this.sourceId = sourceId;
		dataCollection.add(data);
	}
	
	public GenericData(String sourceId, Collection<T> data)
	{
		this.sourceId=sourceId;
		dataCollection.addAll(data);
	}
	
	public void setFile(File file){
		f=file;
	}
	
	public File getFile(){
		return f;
	}
	
	public void addData(T datatype){
		dataCollection.add(datatype);
	}
	
	public Iterable<T> getData(){
		return dataCollection;
	}
	
	public T getSingleData(){
		return dataCollection.get(0);
	}
	
	/*
	private void setDataCollection(ArrayList<T> d){
		dataCollection=d;
	}
	*/
	
	public boolean isMulti(){
		return dataCollection.size()>1;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "GenericData [dataCollection=" + dataCollection + "]";
	}

	@SuppressWarnings("unchecked")
	public void copyData(@SuppressWarnings("rawtypes") GenericData data){
		this.dataCollection= data.dataCollection;
		this.sourceId=data.sourceId;
		this.f=data.f;
	}
	
}
