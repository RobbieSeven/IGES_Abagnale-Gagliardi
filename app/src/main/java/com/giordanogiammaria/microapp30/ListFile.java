package com.giordanogiammaria.microapp30;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.michaelgarnerdev.materialsearchview.MaterialSearchView;

import java.io.File;
import java.util.ArrayList;

public class ListFile extends AppCompatActivity implements MaterialSearchView.SearchViewSearchListener /*,MaterialSearchView.SearchViewVoiceListener*/ {

    private ArrayAdapter<String> adapter;
    MaterialSearchView materialSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listfile);
        ListView listFile;
        listFile = findViewById(R.id.list);
        materialSearchView=findViewById(R.id.material_search_view);
        File localPath=getLocalPath();
        String path = createDir(localPath);//fix
        ArrayList<String> namesOfFile = ReadFileXML(path);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, namesOfFile);
        listFile.setAdapter(adapter);
        listFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String result = adapterView.getItemAtPosition(i).toString();
                setData(result);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        materialSearchView.addListener(this);
    }
    @Override
    public void onBackPressed() {
        if (!materialSearchView.onBackPressed()) {
            super.onBackPressed();
        }

    }


    private void setData(String result) {
        Intent returnIntent = new Intent(result);
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    private File getLocalPath(){
       String nameApp= (String) getApplicationContext().getApplicationInfo().loadLabel(getApplicationContext().getPackageManager());
        return new File(Environment.getExternalStorageDirectory() +
                File.separator +nameApp);
    }

    private String createDir(File path) {
        //create the folder microApp
        boolean isNotCreated;
        isNotCreated = path.mkdir();
        if (isNotCreated)
            System.exit(-1);//memory end
        return path.getPath();
    }

    private ArrayList<String> ReadFileXML(String path) {
        ArrayList<String> namesOfFile;
        namesOfFile =new ArrayList<>();
        File f = new File(path);
        File[] files = f.listFiles();
        if (files!=null)
            for (File inFile : files) {
                namesOfFile.add(inFile.getName());
            }
        return namesOfFile;
    }

    @Override
    public void onSearch(@NonNull String searchTerm) {
        adapter.getFilter().filter(searchTerm);
    }

}