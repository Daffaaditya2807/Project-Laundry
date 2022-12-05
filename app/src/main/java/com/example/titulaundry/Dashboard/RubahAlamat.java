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
import com.example.titulaundry.Model.ResponseAlamat;
import com.example.titulaundry.Model.ResponseUser;
import com.example.titulaundry.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RubahAlamat extends AppCompatActivity {
    EditText setAlamat;
    ApiInterface apiInterface;
    Button edt , submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubah_alamat);
        setSetAlamat ();
        enableDisable();
        notif(RubahAlamat.this);
        updateAlamat();
    }
    
    public void updateAlamat(){
        String id_user = getIntent().getStringExtra("id_user");
        submit = (Button) findViewById(R.id.gantiAlamat);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface = AppClient.getClient().create(ApiInterface.class);
                Call<ResponseAlamat> alamatCall = apiInterface.setAlamat(id_user,setAlamat.getText().toString());
                alamatCall.enqueue(new Callback<ResponseAlamat>() {
                    @Override
                    public void onResponse(Call<ResponseAlamat> call, Response<ResponseAlamat> response) {
                        if (response.body().getKode() == 1){
                            Toast.makeText(RubahAlamat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseAlamat> call, Throwable t) {

                    }
                });
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

    public void setSetAlamat (){
        setAlamat = (EditText) findViewById(R.id.textAlamat);
        String id_user = getIntent().getStringExtra("id_user");
        apiInterface = AppClient.getClient().create(ApiInterface.class);
        Call<ResponseUser> userCall = apiInterface.getDataUser(id_user);
        userCall.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                setAlamat.setText(response.body().getData().getAlamat());
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });
    }

    public void enableDisable(){
        edt = (Button) findViewById(R.id.enbdsb);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!setAlamat.isEnabled()){
                    setAlamat.setEnabled(true);
                } else {
                    setAlamat.setEnabled(false);
                }
            }
        });
    }
}