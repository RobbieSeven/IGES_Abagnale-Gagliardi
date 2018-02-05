package com.giordanogiammaria.microapp30.component_fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.facade.Facade;

import java.util.ArrayList;
import java.util.HashMap;


public class SelectContactFragment extends ComponentFragment{
    View view;
    String name,contact;
    TextView nameTextView,contactTextView;
    Facade facade;
    Contact values;
    @Override
    protected ComponentType setType() {
        return  ComponentType.BLANK;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputTypes=new HashMap<>();
        inputTypes.put("selectContact",DataType.CONTACT);
        return inputTypes;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType> outputType=new ArrayList<>();
        outputType.add(DataType.CONTACT);
        return outputType;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
        GenericData<Contact> data= dataCollection.get("selectContact");
        values=data.getData().get(0);
    }

    @Override
    public HashMap<DataType, GenericData> getOutputsData() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.contactpreview, container, false);
        nameTextView=view.findViewById(R.id.txNome);
        contactTextView= view.findViewById(R.id.txNumero);
        facade=new Facade(view.getContext());
        Intent intent= new Intent(view.getContext(),ContactPickerSampleActivity.class);
        startActivityForResult(intent,1);
        nameTextView.setText(name);
        contactTextView.setText(contact);
        /*values.put(ContactsContract.Data.RAW_CONTACT_ID, facade.getContactIDFromNumber(contact,view.getContext()));
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM);*/
        values=new Contact();// riga da cancellare
        values.setNameContact(facade.getContactName(contact,view.getContext()));
        values.setNumberContact(contact);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent databack) {
            if (requestCode==1){
                name=databack.getStringExtra("name");
                Log.d("number",databack.getStringExtra("number"));
                contact=databack.getStringExtra("number");
                nameTextView.setText(name);
                contactTextView.setText(contact);
            }
    }
}

