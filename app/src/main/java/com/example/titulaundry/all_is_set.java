package com.example.titulaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class all_is_set extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_is_set);
    }

    public void funcLogin(View view) {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
        finish();

    }
}