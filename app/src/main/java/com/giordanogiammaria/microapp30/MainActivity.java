package com.example.giordanogiammaria.microapp30;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView nameFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameFile = findViewById(R.id.namefile);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                1);
    }

    public void browseOnClick(View view) {
        Intent intent= new Intent(getApplicationContext(),ListFile.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                nameFile.setText(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("canceled result","canceled");
            }
        }
    }

    public void runOnClick(View view) {
        Toast.makeText(getApplicationContext(),"TODO",Toast.LENGTH_LONG).show();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //empty
                }
                else {

                    Toast.makeText(MainActivity.this, "Permission denied the application may not work correctly", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
