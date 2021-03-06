package com.giordanogiammaria.microapp30;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.giordanogiammaria.microapp30.manage_file.ManageFile;
import com.michaelgarnerdev.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class ListFile extends AppCompatActivity implements MaterialSearchView.SearchViewSearchListener  {
    /*la classe mostra la lista  dei file xml*/
    private ArrayAdapter<String> adapter;
    MaterialSearchView materialSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //commento di prova
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listfile);
        ListView listFile;
        listFile = findViewById(R.id.list);
        materialSearchView=findViewById(R.id.material_search_view);
        ManageFile manageFile = new ManageFile(getApplicationContext());
        ArrayList<String> namesOfFile;
        namesOfFile = manageFile.getListFile();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, namesOfFile);
        listFile.setAdapter(adapter);
        listFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String result = adapterView.getItemAtPosition(i).toString();
                setData(result);
                Intent intent=new Intent(getApplicationContext(),MicroAppActivity.class);
                intent.putExtra("filePath",result);
                startActivity(intent);
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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void setData(String result) {
        Intent returnIntent = new Intent(this,MicroAppActivity.class);
        returnIntent.putExtra("filePath",result);
        startActivity(returnIntent);
    }

    @Override
    public void onSearch(@NonNull String searchTerm) {
        adapter.getFilter().filter(searchTerm);
    }

}