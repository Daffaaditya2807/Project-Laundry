package com.example.titulaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView takonAkun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        takonAkun = (TextView) findViewById(R.id.takonAkun);
        String text = "<font color=#333333>Belum Punya Akun?</font> <font color=#2f80ed> Sign Up</font>";
        takonAkun.setText(Html.fromHtml(text));
    }
}