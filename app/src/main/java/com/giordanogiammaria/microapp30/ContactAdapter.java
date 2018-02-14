package com.giordanogiammaria.microapp30;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.giordanogiammaria.microapp30.R;
import com.giordanogiammaria.microapp30.component_fragment.Contact;

import java.util.List;


public class ContactAdapter extends BaseAdapter {
    private List<Contact> contacts=null;
    private Context context=null;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contacts.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null) {
            view= LayoutInflater.from(context).inflate(R.layout.recycle_view_layout, null);
        }
        Contact contact=(Contact) getItem(i);
        TextView name= view.findViewById(R.id.nameContact1);
        name.setText(contact.getNameContact());
        TextView phone= view.findViewById(R.id.cel_contact1);
        phone.setText(contact.getNumberContact());
        TextView email= view.findViewById(R.id.email_contact1);
        email.setText(contact.getEmailContact());
        return view;
    }
}
