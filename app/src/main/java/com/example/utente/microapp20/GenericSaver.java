package com.example.utente.microapp20;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GenericSaver {

	public static void save(ContentResolver r, GenericData<?> obj) {

		switch (obj.getDataType()) {
		case IMAGE:
			saveBitmap(r, (ImageData) obj);
			break;
		case STRING:
			saveString((StringData) obj);
			break;
		case LOCATION:
			saveLocation((LocationData) obj);
			break;
		case OBJECT:
			saveObject(obj,true);
		case CONTACT:
			saveContact((ContactData)obj);
			break;
		case MAIL:
			break;
		case URI:
			break;
			
			
		}
	}

	public static void saveObject(GenericData<?> obj,boolean xml) 
	{
		//TODO:salvo il complexData in un file xml
		if(obj instanceof ComplexDataType)
		{
			if(xml)
			//salva come documento xml
			saveAsXml(obj);
			else
			//salva come documento Html
			saveAsHtml(obj);
		}
	}

	private static void saveAsHtml(GenericData<?> obj) 
	{
		ComplexDataType cmp=(ComplexDataType)obj;
		List<MAEntry<String,Object>> list=cmp.getData();
		
		Random rand=new Random();
		int n=rand.nextInt(Short.MAX_VALUE);
		
		String name=list.get(0).getKey()+"_"+n+".html";
		
		File f=new File(FileManagement.getFilesPath(),name);
		
		FileOutputStream fileos;
		try {
			fileos = new FileOutputStream(f);
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(fileos, "UTF-8");
			serializer.startDocument(null, null);
			serializer.docdecl("!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"DTD/xhtml1-transitional.dtd\"");
			serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
			
			serializer.startTag(null, "html");
			serializer.startTag(null, "body");
			serializer.startTag(null, "h2");
			serializer.text(list.get(0).getKey());
			serializer.endTag(null, "h2");
			serializer.startTag(null, "table");
			serializer.attribute(null, "frame", "void");
			serializer.attribute(null, "cellspacing", "20");
			
			preorderHtml(serializer,list);
			
			serializer.endTag(null, "table");
			serializer.endTag(null, "body");
			serializer.endTag(null, "html");
			
			serializer.endDocument();
			serializer.flush();
			
			cmp.setFile(f);
			
		} catch (FileNotFoundException e) {
			Utils.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			Utils.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			Utils.error(e.getMessage(), e);
		} catch (IOException e) {
			Utils.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private static void preorderHtml(XmlSerializer serializer, List<MAEntry<String, Object>> list)
	{
		for(MAEntry<String,Object> entry : list)
		{
			try {
				
				serializer.startTag(null, "tr");
				serializer.startTag(null, "td");
				
				if(entry.getValue() instanceof String)
				{
					serializer.text(entry.getKey());
					serializer.endTag(null, "td");
					
					serializer.startTag(null, "td");
					
					controllaTipo(serializer,(String)entry.getValue());
					
					serializer.endTag(null, "td");
					serializer.endTag(null, "tr");
				}
				else
				{
					serializer.attribute(null, "colspan", "2");
					serializer.attribute(null, "align", "center");
					serializer.attribute(null, "bgcolor", "#cdcdcd");
					serializer.text(entry.getKey());
					serializer.endTag(null, "td");
					serializer.endTag(null, "tr");
					preorderHtml(serializer,(List<MAEntry<String, Object>>) entry.getValue());
				}
				
				//serializer.endTag(null, entry.getKey());
				
				
			} catch (IllegalArgumentException e) {
				Utils.error(e);
			} catch (IllegalStateException e) {
				Utils.error(e);
			} catch (IOException e) {
				Utils.error(e);
			}
		}
	}

	private static void controllaTipo(XmlSerializer serializer, String value) throws IllegalArgumentException, IllegalStateException, IOException
	{
		if(value.startsWith("http://"))
		{
			if(value.endsWith(".jpg") ||
					   value.endsWith(".jpeg")||
					   value.endsWith(".png") ||
					   value.endsWith(".gif") ||
					   value.endsWith(".bmp"))
					{
				serializer.startTag(null, "img");
				serializer.attribute(null, "src", value);
				serializer.endTag(null, "img");
					}
			else
			{
				serializer.startTag(null, "a");
				serializer.attribute(null, "href", value);
				serializer.endTag(null, "a");
			}
		}
		else
			serializer.text(value);
	}

	private static void saveAsXml(GenericData<?> obj) 
	{
		ComplexDataType cmp=(ComplexDataType)obj;
		List<MAEntry<String,Object>> list=cmp.getData();
		
		Random rand=new Random();
		int n=rand.nextInt(Short.MAX_VALUE);
		
		String name=list.get(0).getKey()+"_"+n+".xml";
		
		File f=new File(FileManagement.getFilesPath(),name);
		
		FileOutputStream fileos;
		try {
			fileos = new FileOutputStream(f);
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(fileos, "UTF-8");
			serializer.startDocument(null, null);
			serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
			
			preorder(serializer,list);
			
			serializer.endDocument();
			serializer.flush();
			
			cmp.setFile(f);
			
		} catch (FileNotFoundException e) {
			Utils.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			Utils.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			Utils.error(e.getMessage(), e);
		} catch (IOException e) {
			Utils.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private static void preorder(XmlSerializer serializer, List<MAEntry<String, Object>> list)
	{
		for(MAEntry<String,Object> entry : list)
		{
			try {
				
				serializer.startTag(null, entry.getKey());
				
				if(entry.getValue() instanceof String)
					serializer.text((String) entry.getValue());
				else
					preorder(serializer,(List<MAEntry<String, Object>>) entry.getValue());
				
				serializer.endTag(null, entry.getKey());
				
				
			} catch (IllegalArgumentException e) {
				Utils.error(e);
			} catch (IllegalStateException e) {
				Utils.error(e);
			} catch (IOException e) {
				Utils.error(e);
			}
		}
	}

	@SuppressWarnings("unused")
	public static void saveString(StringData l) {

		for (String s : l.getData()) {

		}

	}

	@SuppressWarnings("unused")
	public static void saveLocation(LocationData g) {
		for (Location l : g.getData()) {

		}

	}
	
	@SuppressWarnings("unused")
	public static void saveContact(ContactData g) {
		for (Contact l : g.getData()) {

		}

	}	
	
	public static void saveBitmap(ContentResolver r, ImageData img) {
		for (Bitmap b : img.getData()) {
			Uri imagesUri = Media.EXTERNAL_CONTENT_URI;// db immagini sd card
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			int millis = calendar.get(Calendar.MILLISECOND);
			
			ContentValues v = new ContentValues();
			String filename = String.format(Locale.getDefault(), "pic" + "_%02d-%02d-%4d-%02d-%02d-%02d-%03d", day, (month + 1), year, hour,
					minute, second,millis);
			v.put(Media.TITLE, "MicroApp Images");
			v.put(Media.DISPLAY_NAME, filename);
			v.put(Media.DESCRIPTION, "MicroApp Camera");
			v.put(Media.DATE_ADDED, calendar.getTimeInMillis());
			v.put(Media.DATE_TAKEN, calendar.getTimeInMillis());
			v.put(Media.DATE_MODIFIED, calendar.getTimeInMillis());
			v.put(Media.MIME_TYPE, "image/jpeg");

			File file = new File(FileManagement.getCameraPath(), filename + ".jpg");

			@SuppressWarnings("unused")
            Uri ptr = r.insert(imagesUri, v);
			// inseriamo i valori e salviamoci l'uri di questa immagine

			try {
				OutputStream outStream = new FileOutputStream(file);
				b.compress(Bitmap.CompressFormat.JPEG, 80, outStream);
				img.setFile(file);
				// decidiamo il formato di compressione e il livello di qualit�
				// (100 qualit� massima/compressione minima)
			} catch (FileNotFoundException e) {
			}

		}
	}
}
