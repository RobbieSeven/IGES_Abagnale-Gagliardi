package com.giordanogiammaria.microapp30;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.giordanogiammaria.microapp30.Activity.CallContactFragment;
import com.giordanogiammaria.microapp30.Activity.LocationFragment;
import com.giordanogiammaria.microapp30.Activity.MapFragment;
import com.giordanogiammaria.microapp30.Activity.SelectContactFragment;
import com.giordanogiammaria.microapp30.Activity.TakePhotoActivity;

public class MicroAppActivity extends AppCompatActivity {
    private MicroAppGenerator generator;
    private String filePath;
    private Intent intent;
    static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.micro_app);
        intent = getIntent();
        filePath = intent.getStringExtra("filePath");
        showFragment(new LocationFragment());
    }

    public void showFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    public void prevOnClick(View view) {
        Toast.makeText(getApplicationContext(),"todo",Toast.LENGTH_LONG).show();

    }


    public void nextOnClick(View view) {

        if (i==0) {
            showFragment(new TakePhotoActivity());
        }
        else if (i==1)
            showFragment(new MapFragment());
        else if (i==2)
            showFragment(new CallContactFragment());
        else
            showFragment(new SelectContactFragment());
        i=(++i)%4;
    }



}
