package com.example.toanapple.s3652979_android_a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClockClick(View view) {
        Intent intent = new Intent(HomeActivity.this, ClockActivity.class );
        startActivity(intent);
    }

    public void onListClick(View view) {
        Intent intent = new Intent(HomeActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
