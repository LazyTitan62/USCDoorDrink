package com.csci310.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EnterLocation extends AppCompatActivity {
    private EditText lati, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_add_location);

        lati = findViewById(R.id.Latitude);
        longi = findViewById(R.id.Longitude);
        Button btn_submit_loc= findViewById(R.id.submit_location);

        //receive user name from intent
        Intent intent = getIntent();
        String merchantName = intent.getExtras().getString("userID");

        btn_submit_loc.setOnClickListener( v->{
            String latitude=lati.getText().toString().trim();
            String longitude=longi.getText().toString().trim();
            //empty latitude
            if(latitude.isEmpty())
            {
                lati.setError("Latitude is empty");
                lati.requestFocus();
                return;
            }
            //empty longitude
            if(longitude.isEmpty())
            {
                longi.setError("Longitude is empty");
                longi.requestFocus();
                return;
            }

            //execute thread to save to sql
            new Thread(() -> {
                insertLocation ins =new insertLocation();
                ins.insertIntoSql(merchantName, Float.parseFloat(latitude), Float.parseFloat(longitude));
                //pass userID to 
                Intent userID = new Intent(EnterLocation.this, MapsActivity.class);
                userID.putExtra("userID", merchantName);
                userID.putExtra("isMerchant", 1);
                startActivity(userID);

            }).start();
        });
    }
}