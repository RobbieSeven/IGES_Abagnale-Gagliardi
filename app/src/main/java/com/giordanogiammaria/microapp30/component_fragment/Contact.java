package com.giordanogiammaria.microapp30.component_fragment;

/**
 * Created by Giordano Giammaria on 01/02/2018.
 */

public class Contact {
    private String nameContact,numberContact,emailContact;

    public Contact(String nameContact, String numberContact, String emailContact) {
        this.nameContact = nameContact;
        this.numberContact = numberContact;
        this.emailContact = emailContact;
    }

    public Contact() {

    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getNumberContact() {
        return numberContact;
    }

    public void setNumberContact(String numberContact) {
        this.numberContact = numberContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }
}
