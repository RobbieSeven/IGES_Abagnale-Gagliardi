package com.giordanogiammaria.microapp30;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.giordanogiammaria.microapp30.exceptions.DataMismatchException;
import com.giordanogiammaria.microapp30.exceptions.DeployFileException;
import com.giordanogiammaria.microapp30.exceptions.MissingDataException;
import com.giordanogiammaria.microapp30.exceptions.NoNextComponentException;
import com.giordanogiammaria.microapp30.exceptions.NoPrevComponentException;
import com.giordanogiammaria.microapp30.exceptions.ParsingException;
import com.giordanogiammaria.microapp30.manage_file.ManageFile;

public class MicroAppActivity extends AppCompatActivity {

    private MicroAppGenerator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.micro_app);
        ManageFile manageFile=new ManageFile(getApplicationContext());
        String path = manageFile.getLocalPath();
        Intent intent = getIntent();
        String filePath = intent.getStringExtra("filePath");
        filePath = path + "/" + filePath;
        Log.e("filePath", filePath);
        try {
            generator = new MicroAppGenerator(filePath);
            showFragment(generator.getStartComponent());
        } catch (DeployFileException | ParsingException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        } catch (NoNextComponentException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), ListFile.class));
        }
    }

    public void prevOnClick(View view) {
        try {
            showFragment(generator.prevCompFragment());
        } catch(NoPrevComponentException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void nextOnClick(View view) {
        try {
            showFragment(generator.nextCompFragment());
        } catch(NoNextComponentException | DataMismatchException | ParsingException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), ListFile.class);
            startActivity(intent);
        } catch (MissingDataException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ListFile.class);
        startActivity(intent);
       finish();
    }

    private void showFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
