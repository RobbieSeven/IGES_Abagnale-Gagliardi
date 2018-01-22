package com.giordanogiammaria.microapp30;

/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public enum ComponentType {
    TAKEPHOTO ("TakePhotoActivity"),
    PREVIEWIMAGE("PreviewImageActivity"),
    CALLCONTACT("CallContactActivity"),
    CALLINTERCEPTOR("CallInterceptorActivity"),
    SELECTCONTACT("SelectContactActivity"),
    SENDMESSAGE("SendMessageActivity"),
    LOCATION("LocationActivity"),
    MAP("MapActivity"),
    SAVE("SaveActivity"),
    SENDMAIL("SendMailActivity");

    private String name="";

    ComponentType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
