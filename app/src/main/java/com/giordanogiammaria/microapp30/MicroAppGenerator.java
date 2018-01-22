package com.giordanogiammaria.microapp30;

import android.app.Activity;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Roberto on 15/01/2018.
 */

public class MicroAppGenerator {

    ArrayList<Component> components;
    DeployParser parser;
    int currentIndex;
    URL filePath;

    public Activity nextComponentActivity(){
        return null;
    }

    public Activity prevComponentActivity(){
        return null;
    }

}
