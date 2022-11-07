package com.example.titulaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.titulaundry.Dashboard.MainMenu;

public class Login extends AppCompatActivity {
    TextView takonAkun , ToRegist;
    Button toLoginDashBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        notif(Login.this);

        takonAkun = (TextView) findViewById(R.id.takonAkun);
        String text = "<font color=#333333>Belum Punya Akun?</font> <font color=#2f80ed> Sign Up</font>";
        takonAkun.setText(Html.fromHtml(text));
        signUp();
        LoginToDashboard();
    }
    public void notif(Activity activity){
        //change color notif bar
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        //set icons notifbar
        View decor = activity.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void signUp(){
        takonAkun = (TextView) findViewById(R.id.takonAkun);
        takonAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }

    public void LoginToDashboard(){
        toLoginDashBoard = (Button) findViewById(R.id.signIn);
        toLoginDashBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });
    }
}