package com.giordanogiammaria.microapp30.manage_file;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class ManageFile {

    private Context context;
    public ManageFile(Context context){
        this.context=context;
    }

    String createDir(File path) {
        //create the folder microApp
        boolean isNotCreated;
        isNotCreated = path.mkdir();
        if (isNotCreated)
            System.exit(-1);//memory end
        return path.getPath();
    }
    private ArrayList<File> readDir(String path) {
        ArrayList<File> listOfFile;
        listOfFile= new ArrayList<>();
        File f = new File(path);
        File[] files = f.listFiles();
        if (files != null)
            listOfFile.addAll(Arrays.asList(files));
        return listOfFile;
    }

    private boolean compareExt(File file){
        String currentExt = android.webkit.MimeTypeMap.getFileExtensionFromUrl(file.getName());
        return currentExt.equalsIgnoreCase("xml");
    }

    private File getLocalPath(Context context){
        String nameApp= (String) context.getApplicationContext().getApplicationInfo().
                loadLabel(context.getApplicationContext().getPackageManager());
        return new File(Environment.getExternalStorageDirectory() +
                File.separator +nameApp);
    }

    private ArrayList<String> filter(ArrayList<File> listFile) {
        ArrayList<String>toReturn=new ArrayList<>();
        for (File file:listFile){
            if (compareExt(file))
                toReturn.add(file.getName());
        }
        return toReturn;
    }
    public ArrayList<String>getListFile(){
        ArrayList<String>toReturn;
        ArrayList<File>app;
        File localPath = this.getLocalPath(context);
        String path = this.createDir(localPath);
        app = this.readDir(path);
        toReturn = this.filter(app);
        return toReturn;
    }
    public String getLocalPath(){
        File f = this.getLocalPath(context);
        return f.getPath();
    }
}
