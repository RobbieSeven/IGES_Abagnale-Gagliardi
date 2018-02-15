package com.giordanogiammaria.microapp30.enumerators;

/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

 public enum ComponentType {
    TAKEPHOTO ("TakePhotoActivity"),
    CALLCONTACT("CallContactActivity"),
    SELECTCONTACT("SelectContactActivity"),
    SENDMESSAGE("SendMessageActivity"),
    LOCATION("LocationActivity"),
    MAP("MapActivity"),
    SAVE("SaveActivity"),
    SENDMAIL("SendMailActivity"),
    BLANK("BlankActivity"),
    BLANKSTART("BlankStartActivity");

    private String name="";

    ComponentType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
