package com.giordanogiammaria.microapp30.enumerators;

public enum ComponentType {

    TAKEPHOTO ("TakePhotoActivity"),
    CALLCONTACT("CallContactActivity"),
    SELECTCONTACT("SelectContactActivity"),
    SENDMESSAGE("SendMessageActivity"),
    LOCATION("LocationActivity"),
    MAP("MapActivity"),
    SENDMAIL("SendMailActivity"),
    BLANK("BlankActivity"),
    BLANKSTART("BlankStartActivity");

    private String name = "";

    ComponentType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
