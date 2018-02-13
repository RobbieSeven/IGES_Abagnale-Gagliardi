
package com.giordanogiammaria.microapp30.component_fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ContactAdapter;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;

import java.util.ArrayList;
import java.util.HashMap;

public class ListContact2 extends ComponentFragment {
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
    public HashMap<DataType, GenericData> getOutputsData() {
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
        ContactAdapter contactAdapter=new ContactAdapter (readListContact(),view.getContext());
        lvItem.setAdapter(contactAdapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //cutString(adapterView.getItemAtPosition(i).toString());
                Object cont = adapterView.getItemAtPosition(i);
                contact=(Contact) cont;
                Snackbar.make(view,"the contact has been selected "+contact.getNameContact(),Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private ArrayList<Contact> readListContact() {
        ContentResolver cr =view.getContext().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        ArrayList<Contact> contacts=new ArrayList<>();
        Contact contact;
        assert cursor != null;
        while (cursor.moveToNext())
        {
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
