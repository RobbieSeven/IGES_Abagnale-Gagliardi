
package com.giordanogiammaria.microapp30.component_fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.giordanogiammaria.microapp30.ContactAdapter;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.manage_contact.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.giordanogiammaria.microapp30.CodeDecode.encodeTobase64;

public class ListContactFragment extends ComponentFragment {
    View view;
    ListView lvItem;
    String displayName="", emailAddress="", phoneNumber="";
    ArrayList<String> contactlist=new ArrayList<>();
    ArrayAdapter<String> itemAdapter;
    Contact contact;

    protected ComponentType setType() {
        return  ComponentType.SELECTCONTACT;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        return new HashMap<>();
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType> outputType=new ArrayList<>();
        outputType.add(DataType.CONTACT);
        return outputType;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
    }

    @Override
    public HashMap<DataType, GenericData> getOutputData() {
        HashMap<DataType,GenericData> outputData=new HashMap<>();
        GenericData<Contact> data= new GenericData<>();
        if (contact!=null) {
            data.addData(contact);
            outputData.put(DataType.CONTACT, data);
        }
        return outputData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list_contact2  , container, false);
        lvItem = (view.findViewById(R.id.listView));
        itemAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1,contactlist);
        ArrayList<Contact> contacts=readListContact();
        Collections.sort(contacts);
        ContactAdapter contactAdapter=new ContactAdapter (contacts,view.getContext());

        lvItem.setAdapter(contactAdapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contact = (Contact) adapterView.getItemAtPosition(i);
                Snackbar.make(view,"the selected contact is: "+contact.getNameContact(),Snackbar.LENGTH_SHORT).show();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                Bitmap bitmap=getContactsDetails(view.getContext(),contact.getNumberContact());
                editor.putString("imagePreference", encodeTobase64(bitmap));
                editor.apply();
            }
        });
        return view;
    }

    private  Bitmap getContactsDetails(Context context, String address) {
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_contact_picture);
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
        Cursor phones = context.getContentResolver().query(contactUri, null, null, null, null);
        assert phones != null;
        while (phones.moveToNext()) {
            String image_uri = phones.getString(phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            if (image_uri != null) {
                try {
                    bp = MediaStore.Images.Media
                            .getBitmap(context.getContentResolver(),
                                    Uri.parse(image_uri));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        phones.close();
        return  bp;
    }

    private ArrayList<Contact> readListContact() {
        ContentResolver cr =view.getContext().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        ArrayList<Contact> contacts=new ArrayList<>();
        Contact contact;
        assert cursor != null;
        while (cursor.moveToNext()) {
            contact=new Contact();
            displayName="";emailAddress=""; phoneNumber="";
            displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            contact.setNameContact(displayName);
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id, null, null);
            assert emails != null;
            if (emails.moveToNext()) {
                emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                contact.setEmailContact(emailAddress);
            }
            emails.close();
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
                assert pCur != null;
                if (pCur.moveToNext()){
                    phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contact.setNumberContact(phoneNumber);
                }
                pCur.close();
            }
            contacts.add(contact);
        }
        cursor.close();
        return contacts;
    }
}
