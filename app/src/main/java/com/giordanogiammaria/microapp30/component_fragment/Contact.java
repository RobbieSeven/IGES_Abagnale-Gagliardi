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

    Contact() {

    }

    public String getNameContact() {
        return nameContact;
    }

    void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    String getNumberContact() {
        return numberContact;
    }

    void setNumberContact(String numberContact) {
        this.numberContact = numberContact;
    }

    String getEmailContact() {
        return emailContact;
    }

    void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }
}
