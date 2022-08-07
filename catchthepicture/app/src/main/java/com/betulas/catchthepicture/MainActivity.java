package com.betulas.catchthepicture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button(View view){
        Intent intent =new Intent(MainActivity.this,gameb.class);
        startActivity(intent);
    }
    public void exitt(String args[]){
        System.exit(0);
    }
}