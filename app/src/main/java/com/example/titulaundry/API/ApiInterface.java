package com.example.titulaundry.API;

import com.example.titulaundry.Model.ResponeRegister;
import com.example.titulaundry.Model.ResponseLogin;
import com.example.titulaundry.Register;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login2.php")
    Call<ResponseLogin> loginResponse(
            @Field("email") String email,
            @Field("password") String pass
    );
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponeRegister> registerResponse(
            @Field("nama") String nama,
            @Field("no_telpon") String telp,
            @Field("email") String email,
            @Field("password") String password
    );
}
