package com.giordanogiammaria.microapp30.component_fragment;

import android.support.annotation.NonNull;



public class Contact implements Comparable<Contact>{
    private String nameContact,numberContact,emailContact;
    private int photoContact;
    public Contact(String nameContact, String numberContact, String emailContact,int photoContact ) {
        this.nameContact = nameContact;
        this.numberContact = numberContact;
        this.emailContact = emailContact;
        this.photoContact = photoContact;
    }

    Contact() {

    }

    public String getNameContact() {
        return nameContact;
    }

    void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getNumberContact() {
        return numberContact;
    }

    void setNumberContact(String numberContact) {
        this.numberContact = numberContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public int getPhotoContact() {
        return photoContact;
    }

    public void setPhotoContact(int photoContact) {
        this.photoContact = photoContact;
    }

    @Override
    public int compareTo(@NonNull Contact contact) {
        String name1=this.getNameContact().toLowerCase();
        String name2=contact.getNameContact().toLowerCase();
        return name1.compareTo(name2);
    }
}
