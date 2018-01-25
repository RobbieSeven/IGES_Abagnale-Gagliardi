package com.giordanogiammaria.microapp30;

import com.giordanogiammaria.microapp30.Activity.CallContactFragment;
import com.giordanogiammaria.microapp30.Activity.CallInterceptorFragment;
import com.giordanogiammaria.microapp30.Activity.LocationFragment;
import com.giordanogiammaria.microapp30.Activity.MapFragment;
import com.giordanogiammaria.microapp30.Activity.PreviewImageFragment;
import com.giordanogiammaria.microapp30.Activity.SaveFragment;
import com.giordanogiammaria.microapp30.Activity.SelectContactFragment;
import com.giordanogiammaria.microapp30.Activity.SendMailFragment;
import com.giordanogiammaria.microapp30.Activity.SendMessageFragment;
import com.giordanogiammaria.microapp30.Activity.TakePhotoFragment;


/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public class FragmentCreator {

    static ComponentFragment getComponentFragment(ComponentType type){

        switch (type) {
            case TAKEPHOTO:
                return new TakePhotoFragment();
            case PREVIEWIMAGE:
                return new PreviewImageFragment();
            case SAVE:
                return new SaveFragment();
            case MAP:
                return new MapFragment();
            case LOCATION:
                return new LocationFragment();
            case CALLCONTACT:
                return new CallContactFragment();
            case CALLINTERCEPTOR:
                return new CallInterceptorFragment();
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
