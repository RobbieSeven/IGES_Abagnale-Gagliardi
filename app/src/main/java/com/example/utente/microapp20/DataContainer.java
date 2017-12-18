package com.example.utente.microapp20;

import java.util.ArrayList;
import java.util.Iterator;

public class DataContainer implements Iterable<GenericData<?>> {

	private GenericData<?> singleData = null;
	private ArrayList<GenericData<?>> dataColl = null;

	public DataContainer(GenericData<?> data) {
		singleData = data;
	}

	public void addData(GenericData<?> data) {
		if (dataColl == null) {
			dataColl = new ArrayList<GenericData<?>>();
			dataColl.add(singleData);
			singleData = null;
		}
		dataColl.add(data);
	}

	public Iterable<GenericData<?>> getDataList() {
		return this;
	}

	public boolean isList() {
		return dataColl != null;
	}

	public Iterator<GenericData<?>> iterator() {
		/*
		return new Iterator<GenericData<?>>() {

			boolean toRead = true;

			public boolean hasNext() {
				if (dataColl == null)
					return toRead;
				return dataColl.iterator().hasNext();
			}

			public GenericData<?> next() {
				if (dataColl == null) {
					toRead = false;
					return singleData;
				}
				return dataColl.iterator().next();
			}

			public void remove() {
				if (dataColl == null)
					return;
				dataColl.iterator().remove();
			}
		};
		*/
		if(dataColl == null)
		{
			ArrayList<GenericData<?>> list=new ArrayList<GenericData<?>>();
			list.add(singleData);
			return list.iterator();
		}
		else
			return dataColl.iterator();
	}

	@Override
	public String toString() {
		return "DataContainer [singleData=" + singleData + ", dataColl=" + dataColl + "]";
	}

}
