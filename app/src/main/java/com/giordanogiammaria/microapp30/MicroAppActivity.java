package com.giordanogiammaria.microapp30;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.giordanogiammaria.microapp30.Activity.CallContactActivity;
import com.giordanogiammaria.microapp30.Activity.MapActivity;
import com.giordanogiammaria.microapp30.Activity.SelectContactActivity;
import com.giordanogiammaria.microapp30.Activity.SendMailActivity;
import com.giordanogiammaria.microapp30.Activity.SendMessageActivity;
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
        showFragment(new SendMailActivity());
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


    }


    public void nextOnClick(View view) {

        if (i==0) {
            showFragment(new TakePhotoActivity());
        }
        else if (i==1)
            showFragment(new MapActivity());
        else if (i==2)
            showFragment(new CallContactActivity());
        else
            showFragment(new SelectContactActivity());
        i=(++i)%4;
    }



}
