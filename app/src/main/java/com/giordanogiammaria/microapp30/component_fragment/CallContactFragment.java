package com.giordanogiammaria.microapp30.component_fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.facade.Facade;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;



public class CallContactFragment extends ComponentFragment{
    View view;
    TextView nameContact;
    Facade facade;
    String number;
    Contact values;
    @Override
    protected ComponentType setType() {
        return ComponentType.CALLCONTACT;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputTypes=new HashMap<>();
        inputTypes.put("contact",DataType.CONTACT);
        return  inputTypes;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        return null;
    }

    @Override
    public void setInputsData(HashMap<String,GenericData> dataCollection) {
        GenericData<Contact> data = dataCollection.get("contact");
        values=data.getData().get(0);

    }
    @Override
    public HashMap<DataType,GenericData> getOutputsData() {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.contactpreview, container, false);
        nameContact=view.findViewById(R.id.txNome);
        // get the reference of Button
        facade=new Facade(view.getContext());
        number =values.getNumberContact();
        nameContact.setText(facade.getContactName(number,view.getContext()));
        call(number);
        return view;
    }
    @SuppressLint("MissingPermission")
    public void call(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number ));
        startActivity(intent);
    }

}
