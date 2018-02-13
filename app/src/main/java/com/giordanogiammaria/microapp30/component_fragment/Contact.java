package com.giordanogiammaria.microapp30.component_fragment;

/**
 * Created by Giordano Giammaria on 01/02/2018.
 */

public class Contact {
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
}
