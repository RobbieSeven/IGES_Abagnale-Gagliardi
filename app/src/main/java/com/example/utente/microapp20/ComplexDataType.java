package com.example.utente.microapp20;

import org.w3c.dom.Element;

import java.util.Collection;
import java.util.List;


public class ComplexDataType extends GenericData<MAEntry<String,Object>>
{
	private Element complexElement;

	public ComplexDataType(String sourceId, Collection<MAEntry<String, Object>> data)
	{
		super(sourceId, data);
		
	}

	public ComplexDataType(String sourceId, MAEntry<String, Object> data)
	{
		super(sourceId, data);
		
	}

	@Override
	public DataType getDataType() 
	{
		
		return DataType.OBJECT;
	}
	
	public Element getComplexElement() {
		return complexElement;
	}

	public void setComplexElement(Element cmp) {
		complexElement = cmp;
	}
	
	public List<MAEntry<String,Object>> getData()
	{
		return dataCollection;
	}
	
	public String toString()
	{
		StringBuffer buff=new StringBuffer();
		//buff.append("ComplexDataType\n");
		for(int i=0;i<this.dataCollection.size();i++)
		{
			buff.append(readData(dataCollection.get(i)));
			buff.append("\n");
		}
		
		return buff.toString();
	}

	@SuppressWarnings("unchecked")
	private String readData(MAEntry<String, Object> entry)
	{
		StringBuffer buff=new StringBuffer();
		
		if(entry.getValue() instanceof String)
			buff.append(entry.toString()+",");
		else
		{
			List<MAEntry<String,Object>> l=(List<MAEntry<String, Object>>) entry.getValue();
			buff.append("\""+entry.getKey()+"\"[ ");
			for(int i=0;i<l.size();i++)
			{
				buff.append(readData(l.get(i)));
			}
			buff.append("]\n");
		}
		return buff.toString();
	}
	

	public void copyData(@SuppressWarnings("rawtypes") GenericData  data)
	{
		ComplexDataType dt=(ComplexDataType)data;
		this.complexElement=dt.getComplexElement();
		super.copyData(data);
	}

}
