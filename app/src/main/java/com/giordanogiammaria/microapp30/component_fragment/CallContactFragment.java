package com.giordanogiammaria.microapp30.component_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.facade.Facade;
import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.R;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.TELEPHONY_SERVICE;
import static com.giordanogiammaria.microapp30.CodeDecode.decodeBase64;


public class CallContactFragment extends ComponentFragment{
    View view;
    TextView nameContact;
    Facade facade;
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
        facade=new Facade(view.getContext());
        number =values.getNumberContact();
        nameContact.setText(facade.getContactName(number,view.getContext()));
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

