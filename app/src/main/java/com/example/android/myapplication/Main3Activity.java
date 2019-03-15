package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void Com(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void Human(View v)
    {
        Intent i = new Intent(this,Main4Activity.class);
        startActivity(i);
    }
}
