package com.giordanogiammaria.microapp30.component_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



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
    public HashMap<DataType,GenericData> getOutputsData() {
        return new HashMap<>();
    }


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
        Bitmap bitmap=getContactsDetails(view.getContext(),number);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
        imageViewContact.setImageBitmap(resized);
        return view;
    }
    @SuppressLint("MissingPermission")
    public void call(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number ));
        startActivity(intent);
    }

    public  Bitmap getContactsDetails(Context context,String address) {
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_contact_picture);
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
        // querying contact data store
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            phones.close();
        }
        return   bp;
    }
}
