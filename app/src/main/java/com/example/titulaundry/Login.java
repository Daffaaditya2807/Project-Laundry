package com.example.titulaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Dashboard.MainMenu;
import com.example.titulaundry.Model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView takonAkun , ToLupaPw;
    EditText getEmail , getPassword;
    Button toLoginDashBoard;
    String[] user;
    ApiInterface apiInterface;
    protected Cursor cursor;
    public static Login lg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lg = this;

        notif(Login.this);

        takonAkun = (TextView) findViewById(R.id.takonAkun);
        String text = "<font color=#333333>Belum Punya Akun?</font> <font color=#2f80ed> Sign Up</font>";
        takonAkun.setText(Html.fromHtml(text));
        signUp();
        LoginToConfirm();
        lupaPassword();
    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
        finish();
    }

    public void lupaPassword(){
        ToLupaPw = (TextView) findViewById(R.id.lupaPassword);
        ToLupaPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LupaPassword.class);
                startActivity(i);
            }
        });
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

    public void LoginToConfirm(){
        getEmail = (EditText) findViewById(R.id.getEmail);
        getPassword = (EditText) findViewById(R.id.getPassword);

        toLoginDashBoard = (Button) findViewById(R.id.signIn);
        toLoginDashBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userCheck = getEmail.getText().toString();
                String passCheck = getPassword.getText().toString();
                //nanti hapus
//                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
//                startActivity(intent);
                //end

                if (userCheck.equals("") || passCheck.equals("")){
                    Toast.makeText(Login.this,"Mohon Isi Semua Data",Toast.LENGTH_LONG).show();
                } else {
                    apiInterface = AppClient.getClient().create(ApiInterface.class);
                    Call<ResponseLogin> loginCall = apiInterface.loginResponse(getEmail.getText().toString(),getPassword.getText().toString());
                    loginCall.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                            if (response.body() != null && response.isSuccessful() && response.body().isStatus())  {
                                String verif = response.body().getData().getVerifyStatus();
                                if (!verif.equals("verifikasi")){
                                    Toast.makeText(Login.this,"Akun Belum Verified",Toast.LENGTH_LONG).show();

                                } else {

                                    //Ini untuk pindah
                                    Toast.makeText(Login.this,response.body().getData().getEmail(),Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                                    intent.putExtra("email",userCheck);
                                    intent.putExtra("id_user",response.body().getData().getIdUser());
                                    System.out.println("ID User pada login ="+response.body().getData().getIdUser());
                                    startActivity(intent);
                                    finish();
                                }

                            } else {
//                                System.out.println("tesss  = "+response.body().getData());
                                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



            }

            }
        });
    }
}