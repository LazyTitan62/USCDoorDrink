package com.csci310.uscdoordrink;
import java.lang.Object;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new Thread(new Runnable() {
            @Override
            public void run() {
                insert in=new insert();
                in.doInBackground();
            }
        }).start();

    }
}