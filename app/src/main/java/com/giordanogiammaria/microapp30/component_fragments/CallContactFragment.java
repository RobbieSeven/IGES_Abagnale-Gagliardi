package com.giordanogiammaria.microapp30.component_fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.manage_contact.Contact;

import java.util.ArrayList;
import java.util.HashMap;

import static com.giordanogiammaria.microapp30.CodeDecode.decodeBase64;


public class CallContactFragment extends ComponentFragment{
    View view;
    TextView nameContact;
    String number;
    Contact values;
    ImageView imageViewContact;
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
        return new ArrayList<>();
    }

    @Override
    public void setInputsData(HashMap<String,GenericData> dataCollection) {

        GenericData<Contact> data = dataCollection.get("contact");
        values=data.getData().get(0);
    }
    @Override
    public HashMap<DataType,GenericData> getOutputData() {
        return new HashMap<>();
    }
    //infiltro il layout di callContact
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.contactpreview, container, false);
        nameContact=view.findViewById(R.id.txNome);
        imageViewContact=view.findViewById(R.id.photoContact);
        number =values.getNumberContact();
        nameContact.setText(Contact.getContactName(number,view.getContext()));
        call(number);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String data = prefs.getString("imagePreference", "no id");
        imageViewContact.setImageBitmap(decodeBase64(data));
        return view;
    }

    @SuppressLint("MissingPermission")
    public void call(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number ));
        startActivity(intent);
    }
}

