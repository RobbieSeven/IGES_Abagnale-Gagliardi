package com.giordanogiammaria.microapp30.component_fragment;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;


/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public class ComponentFragmentCreator {

    public static ComponentFragment getComponentFragment(ComponentType type) {
        switch (type) {
            case BLANK:
                return new BlankFragment();
            case TAKEPHOTO:
                return new TakePhotoFragment();
            case SAVE:
                return new SaveFragment();
            case MAP:
                return new MapFragment();
            case LOCATION:
                return new LocationFragment();
            case CALLCONTACT:
                return new CallContactFragment();
            case SELECTCONTACT:
                return new SelectContactFragment();
            case SENDMESSAGE:
                return new SendMessageFragment();
            case SENDMAIL:
                return new SendMailFragment();
        }
        return null;
    }

}