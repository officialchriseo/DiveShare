package ng.com.blogspot.httpofficialceo.diveshare;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.security.AccessController.getContext;
import static ng.com.blogspot.httpofficialceo.diveshare.ContactsFragment.RequestPermissionCode;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApplication;
    private boolean backPressedOnce = false;
    private Handler statusUpdateHandler;
    private Runnable statusUpdateRunnable;

    private CircleImageView userImage;
    private Button shareResources, receiveResources, shareApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextOfApplication = getApplicationContext();

        shareResources = (Button) findViewById(R.id.share_resource_button);
        receiveResources = (Button) findViewById(R.id.receive_resource_button);
        shareApp = (Button) findViewById(R.id.share_app_button);
        userImage = (CircleImageView) findViewById(R.id.user_image);

        EnableRuntimePermission();


        shareResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ShareResources.class);
                startActivity(myIntent);

            }
        });

        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
    }

    public void EnableRuntimePermission(){


        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(MainActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    public void shareApp(){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
//        i.setClassName("com.xxx.your_package_name",
//                "com.xxx.your_class_name");

        startActivity(i);
    }

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("QUIT");
        alertDialog.setMessage("Do you want to exit this app?");
        alertDialog.setIcon(R.drawable.ic_exit_app_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                return;
            }
        });
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (statusUpdateHandler != null){
        }
    }
}
