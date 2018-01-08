package com.giordanogiammaria.microapp30.Facade;

import android.content.Context;

import com.giordanogiammaria.microapp30.Subsystem.ManageFile;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Giordano Giammaria on 08/01/2018.
 */

public class Facade implements IFacade{
    private Context context;

    public Facade(Context context){
        this.context=context;
    }

    @Override
    public ArrayList<String>getListFile(){
        ArrayList<String>toReturn;
        ManageFile manageFile=new ManageFile();
        File localPath=manageFile.getLocalPath(context);
        String path= manageFile.createDir(localPath);
        toReturn=manageFile.ReadFileXML(path);
        return toReturn;
    }
}
