package com.giordanogiammaria.microapp30.Subsystem;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<File> ReadDir(String path) {
        ArrayList<File> listOfFile;
        listOfFile= new ArrayList<>();
        File f = new File(path);
        File[] files = f.listFiles();
        if (files != null)
            listOfFile.addAll(Arrays.asList(files));
        return listOfFile;
    }

    public boolean compareExt(File file,String ext){
        String currentExt = android.webkit.MimeTypeMap.getFileExtensionFromUrl(file.getName());
        return currentExt.equalsIgnoreCase(ext);
    }

    public File getLocalPath(Context context){
        String nameApp= (String) context.getApplicationContext().getApplicationInfo().
                loadLabel(context.getApplicationContext().getPackageManager());
        return new File(Environment.getExternalStorageDirectory() +
                File.separator +nameApp);
    }

    public ArrayList<String> filter(ArrayList<File> listFile, String ext) {
        ArrayList<String>toReturn=new ArrayList<>();
        for (File file:listFile){
            if (compareExt(file,ext))
                toReturn.add(file.getName());
        }
        return toReturn;
    }
}
