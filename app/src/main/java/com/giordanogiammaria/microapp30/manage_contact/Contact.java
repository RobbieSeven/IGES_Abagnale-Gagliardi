package com.giordanogiammaria.microapp30.manage_contact;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;



public class Contact implements Comparable<Contact>{
    private String nameContact,numberContact,emailContact;
    private Bitmap photoContact;
    public Contact(String nameContact, String numberContact, String emailContact,Bitmap photoContact ) {
        this.nameContact = nameContact;
        this.numberContact = numberContact;
        this.emailContact = emailContact;
        this.photoContact = photoContact;
    }

    public Contact() {

    }
    public static String getContactName(final String phoneNumber, Context context) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName = "";
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName = cursor.getString(0);
            }
            cursor.close();
        }
        return contactName;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getNumberContact() {
        return numberContact;
    }

    public void setNumberContact(String numberContact) {
        this.numberContact = numberContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public Bitmap getPhotoContact() {
        return photoContact;
    }

    public void setPhotoContact(Bitmap photoContact) {
        this.photoContact = photoContact;
    }

    @Override
    public int compareTo(@NonNull Contact contact) {
        String name1=this.getNameContact().toLowerCase();
        String name2=contact.getNameContact().toLowerCase();
        return name1.compareTo(name2);
    }
}
