package com.example.titulaundry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Model.ResponeRegister;
import com.example.titulaundry.db_help.Database_Tb_user;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextView takonAkun,bckToLogin;
    EditText txNama , txTlp,txEmail,txPw,matchPw;
    Button btnRegist;
    CheckBox syrt;
    ApiInterface apiInterface;
    Database_Tb_user dbcenter;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        notif(Register.this);
        dbcenter = new Database_Tb_user(this);
        setTectxtColour();
        RegistAccount();
        setCheckBox();
        kembaliKeLogin();

    }

    public void setCheckBox(){
        syrt = (CheckBox) findViewById(R.id.syarat);
        syrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (syrt.isChecked()){
                    syrt.setBackgroundTintList(getResources().getColorStateList(R.color.niceBlue));
                    Toast.makeText(Register.this, "turu", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void setTectxtColour(){
        takonAkun = (TextView) findViewById(R.id.takonAkun);
        String text = "<font color=#333333>Sudah Punya Akun?</font> <font color=#2f80ed> Sign In</font>";
        takonAkun.setText(Html.fromHtml(text));
    }
    public void kembaliKeLogin(){
        bckToLogin = (TextView) findViewById(R.id.takonAkun);
        bckToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
    public void RegistAccount(){
        txNama = (EditText) findViewById(R.id.inputNama);
        txTlp = (EditText) findViewById(R.id.txTlp);
        txEmail = (EditText) findViewById(R.id.txEmail);
        txPw = (EditText) findViewById(R.id.txPw);
        matchPw = (EditText) findViewById(R.id.matchPw);
        btnRegist = (Button)findViewById(R.id.signUP);
        syrt = (CheckBox) findViewById(R.id.syarat);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getNama = txNama.getText().toString().trim();
                String getTelp = txTlp.getText().toString().trim();
                String getEmail = txEmail.getText().toString().trim();
                String getPw = txPw.getText().toString().trim();
                String matPw = matchPw.getText().toString().trim();

                int min = 1;
                int max = 10000;

                //Generate random int value from 50 to 100
                System.out.println("Random value in int from "+min+" to "+max+ ":");
                int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
                System.out.println(random_int);
                String numberRandom = String.valueOf(random_int);

                String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";

                if (!getPw.equals(matPw)){
                    Toast.makeText(Register.this,"Password Tidak Sama",Toast.LENGTH_LONG).show();
                } else if  (getNama.equals("")||getTelp.equals("")||getEmail.equals("")||getPw.equals("")||matPw.equals("")||!syrt.isChecked()){
                    Toast.makeText(Register.this,"Harap Lengkapi",Toast.LENGTH_LONG).show();
                } else if (!getEmail.matches(emailPattern)||!getEmail.contains("@gmail.com")){
                    Toast.makeText(Register.this,"Email tidak valid",Toast.LENGTH_LONG).show();
                } else{
                    apiInterface = AppClient.getClient().create(ApiInterface.class);
                    Call<ResponeRegister> simpan = apiInterface.registerResponse(getNama,getTelp,getEmail,getPw);
                    simpan.enqueue(new Callback<ResponeRegister>() {
                        @Override
                        public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                            int kode = response.body().getKode();
                            if (kode == 1){
                                Toast.makeText(Register.this,"Berhasil Daftar",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),Konfirmasi.class);
                                 startActivity(i);
                            } else {
                                Toast.makeText(Register.this,"Gagal Daftar",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponeRegister> call, Throwable t) {

                        }
                    });


                }
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
}