package com.example.eotassignment202302;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    public void startDatabaseActivity(View view) {
        startActivity(new Intent(this, DatabaseActivity.class));
    }//startDatabaseActivity

    public void startFragmentActivity(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }//startFragmentActivity

    public void startListViewActivity(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }//startListViewActivity
}//MainActivity