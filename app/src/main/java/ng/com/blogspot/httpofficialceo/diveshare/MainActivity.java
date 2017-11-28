package ng.com.blogspot.httpofficialceo.diveshare;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView userImage;
    private Button shareResources, receiveResources, shareApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareResources = (Button) findViewById(R.id.share_resource_button);
        receiveResources = (Button) findViewById(R.id.receive_resource_button);
        shareApp = (Button) findViewById(R.id.share_app_button);
        userImage = (CircleImageView) findViewById(R.id.user_image);


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

    public void shareApp(){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
//        i.setClassName("com.xxx.your_package_name",
//                "com.xxx.your_class_name");

        startActivity(i);
    }


}
