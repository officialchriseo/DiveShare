package ng.com.blogspot.httpofficialceo.diveshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import ebongcreative.max.ssp.SSP;
import ebongcreative.max.ssp.SharedPreferenceStorage;

public class BarCodeActivity extends AppCompatActivity {

    ArrayList<String> selectedItem;
    SSP simplePreference;
    TextView contactName, contactNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);

        contactName = (TextView) findViewById(R.id.personName);
        contactNumber = (TextView) findViewById(R.id.personNumber);

        getIntents();

    }

    private void getIntents(){
        Intent myIntent = getIntent();
        String name = myIntent.getStringExtra("CONTACT_NAME");
        String number = myIntent.getStringExtra("CONTACT_NUMBER");

        contactName.setText(name);
        contactNumber.setText(number);
    }
}
