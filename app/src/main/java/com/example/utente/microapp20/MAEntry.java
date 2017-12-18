package com.example.utente.microapp20;

import java.io.Serializable;


public class MAEntry<K,V> implements Serializable
{
	private static final long serialVersionUID = -810366412914357526L;
	private K key;
	private V value;
	
	public MAEntry()
	{
		
	}
	
	public MAEntry(K k, V v)
	{
		key=k;
		value=v;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	public String toString()
	{
		return "{ \""+key+"\":\""+value+"\"}";
	}
}
