package com.giordanogiammaria.microapp30;

import android.app.Activity;

import com.giordanogiammaria.microapp30.Activity.CallContactActivity;
import com.giordanogiammaria.microapp30.Activity.CallInterceptorActivity;
import com.giordanogiammaria.microapp30.Activity.LocationActivity;
import com.giordanogiammaria.microapp30.Activity.MapActivity;
import com.giordanogiammaria.microapp30.Activity.PreviewImageActivity;
import com.giordanogiammaria.microapp30.Activity.SaveActivity;
import com.giordanogiammaria.microapp30.Activity.SelectContactActivity;
import com.giordanogiammaria.microapp30.Activity.SendMailActivity;
import com.giordanogiammaria.microapp30.Activity.SendMessageActivity;
import com.giordanogiammaria.microapp30.Activity.TakePhotoActivity;


/**
 * Created by Giuseppe Abagnale on 22/01/2018.
 */

public class ComponentCreator {

    static ComponentActivity getComponentActivity(ComponentType type){

        switch (type) {
            case TAKEPHOTO:
                return new TakePhotoActivity();
            case PREVIEWIMAGE:
                return new PreviewImageActivity();
            case SAVE:
                return new SaveActivity();
            case MAP:
                return new MapActivity();
            case LOCATION:
                return new LocationActivity();
            case CALLCONTACT:
                return new CallContactActivity();
            case CALLINTERCEPTOR:
                return new CallInterceptorActivity();
            case SELECTCONTACT:
                return new SelectContactActivity();
            case SENDMESSAGE:
                return new SendMessageActivity();
            case SENDMAIL:
                return new SendMailActivity();

        }
        return null;
    }

}
