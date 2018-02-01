package com.giordanogiammaria.microapp30;

import com.giordanogiammaria.microapp30.Activity.BlankFragment;
import com.giordanogiammaria.microapp30.Activity.CallContactFragment;
import com.giordanogiammaria.microapp30.Activity.LocationFragment;
import com.giordanogiammaria.microapp30.Activity.MapFragment;
import com.giordanogiammaria.microapp30.Activity.SaveFragment;
import com.giordanogiammaria.microapp30.Activity.SelectContactFragment;
import com.giordanogiammaria.microapp30.Activity.SendMailFragment;
import com.giordanogiammaria.microapp30.Activity.SendMessageFragment;
import com.giordanogiammaria.microapp30.Activity.TakePhotoFragment;


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
