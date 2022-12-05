package com.example.titulaundry.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Model.ResponseRubahPw;
import com.example.titulaundry.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ubah_password extends AppCompatActivity {

    EditText passwordLama , passwordBaru , confirmPassword;
    Button submitPassword;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        notif(ubah_password.this);
        rubahPassword();
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

    public void rubahPassword(){
        passwordLama = (EditText) findViewById(R.id.passwordLama);
        passwordBaru = (EditText) findViewById(R.id.passwordBaru);
        confirmPassword = (EditText) findViewById(R.id.confrimPassword);
        submitPassword = (Button) findViewById(R.id.simpanPerubahan);

        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordBaru.getText().toString().equals(confirmPassword.getText().toString())){
                    apiInterface = AppClient.getClient().create(ApiInterface.class);
                    Call<ResponseRubahPw> pwCall = apiInterface.UbahPw(getIntent().getStringExtra("id_user"),passwordLama.getText().toString(),passwordBaru.getText().toString());
                    pwCall.enqueue(new Callback<ResponseRubahPw>() {
                        @Override
                        public void onResponse(Call<ResponseRubahPw> call, Response<ResponseRubahPw> response) {

                            if (response.body().getKode() == 1){
                                Toast.makeText(ubah_password.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            } else if (response.body().getKode() == 2){
                                Toast.makeText(ubah_password.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseRubahPw> call, Throwable t) {
                            Toast.makeText(ubah_password.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ubah_password.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}