package com.giordanogiammaria.microapp30.Facade;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.giordanogiammaria.microapp30.Subsystem.ManageFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Giordano Giammaria on 08/01/2018.
 */

public class Facade {
    private Context context;

    public Facade(Context context){
        this.context=context;
    }


    public ArrayList<String>getListFile(){
        ArrayList<String>toReturn;
        ArrayList<File>app;
        ManageFile manageFile=new ManageFile();
        File localPath=manageFile.getLocalPath(context);
        String path= manageFile.createDir(localPath);
        app=manageFile.ReadDir(path);
        toReturn=manageFile.filter(app,"xml");
        return toReturn;
    }
    public String getContactName(final String phoneNumber, Context context)
    {
        Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName="";
        Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName=cursor.getString(0);
            }
            cursor.close();
        }
        return contactName;
    }


    public  int getContactIDFromNumber(String contactNumber,Context context)
    {
        contactNumber = Uri.encode(contactNumber);
        int phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,contactNumber),new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while(contactLookupCursor.moveToNext()){
            phoneContactID = contactLookupCursor.getInt(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }
}
