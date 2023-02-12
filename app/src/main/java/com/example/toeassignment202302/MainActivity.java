package com.example.toeassignment202302;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allinone202302.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    public void startDatabaseActivity(View view) {
        startActivity(new Intent(this, DatabaseActivity.class));
    }//startDatabaseActivity
}//MainActivity