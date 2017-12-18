package com.example.utente.microapp20;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Contact {

	private String name;
	private Bitmap img;
	private List<String> phones;
	private Uri lookupUri;
	private List<String> mails;

	public Contact() {
		this.name = "";
		this.img = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
		this.mails = new LinkedList<String>();
		this.phones = new LinkedList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getImg() {
		return img;
	}

	public void setImg(Bitmap img) {
		this.img = img;
	}

	public List<String> getPhones() {
		return phones;
	}

	public String getPhone() {
		if(phones.size() > 0)
			return phones.get(0);
		
		return "";
	}

	public void setPhone(String phone) {
		this.phones.add(phone);
	}

	public void setLookUpURI(Uri lookupUri) {
		this.lookupUri = lookupUri;
	}

	public Uri getLookUpURI() {
		return lookupUri;
	}

	public List<String> getMails() {
		return mails;
	}

	public void setMail(String mail) {
		this.mails.add(mail);
	}

	public String toString() {
		return "Contact:\n" + "name:" + name + "\n" 
				+ "phones:" + Arrays.toString(phones.toArray()) + "\n"
				+ "mails:" + Arrays.toString(mails.toArray()) + "\n";
	}

	public void load(Context con, Uri contactData) {
		String pname;
		String pphone;
		ContentResolver cr = con.getContentResolver();

		this.setLookUpURI(contactData);

		String[] projection = new String[] { ContactsContract.Contacts.PHOTO_ID };

		Cursor cc = con.getContentResolver().query(contactData, projection, null, null, null);

		if (cc.moveToFirst()) {
			final String photoId = cc.getString(cc.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
			if (photoId != null) {
				final Cursor photo = con
						.getContentResolver()
						.query(
						// final Cursor photo = managedQuery(
						Data.CONTENT_URI, new String[] { Photo.PHOTO }, Data._ID + "=?", new String[] { photoId }, null);

				// Convert photo blob to a bitmap
				if (photo.moveToFirst()) {
					byte[] photoBlob = photo.getBlob(photo.getColumnIndex(Photo.PHOTO));
					Bitmap photoBitmap = BitmapFactory.decodeByteArray(photoBlob, 0, photoBlob.length);
					this.setImg(photoBitmap);
				}

			}

		}

		Cursor c = con.getContentResolver().query(contactData, null, null, null, null);

		if (c.moveToFirst()) {
			String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
			pname = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			this.setName(pname);			
			
			if (Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
				Cursor pCur = cr.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = ?", new String[] { id }, null);

				while (pCur.moveToNext()) {
					pphone = pCur.getString(pCur.getColumnIndex(Phone.NUMBER));
					this.setPhone(pphone);
				}
			}

		}

		cc = con.getContentResolver().query(contactData,
				new String[] { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME }, null, null,
				null);
		if (cc.getCount() > 0) {
			while (cc.moveToNext()) {
				String id = cc.getString(0);
				Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, // email
																							// uri
						null, Phone.CONTACT_ID + " = ?", // Clausola
																							// where
						new String[] { id }, // Parametro
						null);
				while (pCur.moveToNext()) {
					String email = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA1));
					this.setMail(email);
				}
			}
		}

	}

}
