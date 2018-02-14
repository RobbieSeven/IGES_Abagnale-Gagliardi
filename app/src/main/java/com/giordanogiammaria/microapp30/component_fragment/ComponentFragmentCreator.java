package com.giordanogiammaria.microapp30.component_fragment;

import com.giordanogiammaria.microapp30.Subsystem.MissingComponentTypeException;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;


public class ComponentFragmentCreator {

    public static ComponentFragment getComponentFragment(ComponentType type) throws MissingComponentTypeException {
        switch (type) {
            case BLANK:
                return new BlankFragment();
            case BLANKSTART:
                return new BlankStartFragment();
            case TAKEPHOTO:
                return new TakePhotoFragment();
            case MAP:
                return new MapFragment();
            case LOCATION:
                return new LocationFragment();
            case CALLCONTACT:
                return new CallContactFragment();
            case SELECTCONTACT:
                return new ListContactFragment();
            case SENDMESSAGE:
                return new SendMessageFragment();
            case SENDMAIL:
                return new SendMailFragment();
            default:
                break;
        }
        throw new MissingComponentTypeException(type.toString());
    }

}
