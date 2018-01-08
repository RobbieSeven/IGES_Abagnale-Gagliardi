package com.giordanogiammaria.microapp30.Subsystem;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Giordano Giammaria on 08/01/2018.
 */

public class ManageFile {
    public ManageFile(){

    }

    public String createDir(File path) {
        //create the folder microApp
        boolean isNotCreated;
        isNotCreated = path.mkdir();
        if (isNotCreated)
            System.exit(-1);//memory end
        return path.getPath();
    }
    public ArrayList<String> ReadFileXML(String path) {
        String ext;
        ArrayList<String> namesOfFile;
        namesOfFile = new ArrayList<>();
        File f = new File(path);
        File[] files = f.listFiles();
        if (files != null)
            for (File inFile : files) {
                ext = android.webkit.MimeTypeMap.getFileExtensionFromUrl(inFile.getName());
                if (ext.equalsIgnoreCase("xml"))
                    namesOfFile.add(inFile.getName());
            }
        return namesOfFile;
    }
    public File getLocalPath(Context context){
        String nameApp= (String) context.getApplicationContext().getApplicationInfo().loadLabel(context.getApplicationContext().getPackageManager());
        return new File(Environment.getExternalStorageDirectory() +
                File.separator +nameApp);
    }
}
