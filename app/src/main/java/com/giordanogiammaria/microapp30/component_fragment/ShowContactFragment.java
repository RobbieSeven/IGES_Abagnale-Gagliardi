package com.giordanogiammaria.microapp30.component_fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowContactFragment extends ComponentFragment {
    View view;
    ListView lvItem;
    String displayName="", emailAddress="", phoneNumber="";
    ArrayList<String> contactlist=new ArrayList<>();
    ArrayAdapter<String> itemAdapter;
    Contact contact;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_temp  , container, false);
        lvItem = (view.findViewById(R.id.listView_items));
        itemAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1,contactlist);
        contact=new Contact();
        lvItem.setAdapter(itemAdapter);
        readContacts();
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cutString(adapterView.getItemAtPosition(i).toString());
                Snackbar.make(view,"the contact has been selected",Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void cutString(String itemAtPosition) {
        String[] cont=itemAtPosition.split(",");
        contact.setNameContact(cont[0]);
        contact.setNumberContact(cont[1]);
        contact.setEmailContact(cont[2]);
    }

    private void readContacts() {
        ContentResolver cr =view.getContext().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        assert cursor != null;
        while (cursor.moveToNext())
        {
            displayName="";emailAddress=""; phoneNumber="";
            displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + id, null, null);
            assert emails != null;
            if (emails.moveToNext()) {
                emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            }

            emails.close();
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{id}, null);
                assert pCur != null;
                if (pCur.moveToNext()){
                    phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                pCur.close();
            }
            contactlist.add(displayName+",\n "+phoneNumber+",\n"+ emailAddress+"\n");
            itemAdapter.notifyDataSetChanged();
        }
        cursor.close();
    }

    @Override
    protected ComponentType setType() {
        return null;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        return null;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return null;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {

    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        HashMap<DataType,GenericData> outputData=new HashMap<>();
        GenericData<Contact> data= new GenericData<>();
        data.addData(contact);
        outputData.put(DataType.CONTACT,data);
        return outputData;
    }
}
