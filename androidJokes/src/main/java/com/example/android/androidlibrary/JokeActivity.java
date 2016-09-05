package com.example.android.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getIntent() != null){
            if (getIntent().hasExtra(Intent.EXTRA_TEXT)){
                TextView tv = (TextView) findViewById(R.id.joke_textview);
                String joke = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                tv.setText(joke);
            }
        }
    }
}
