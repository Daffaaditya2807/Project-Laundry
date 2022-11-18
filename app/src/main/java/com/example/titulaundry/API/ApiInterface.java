package com.example.titulaundry.API;

import com.example.titulaundry.Model.ResponeLogin;
import com.example.titulaundry.Register;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponeLogin> loginResponse(
            @Field("email") String email,
            @Field("password") String pass
    );
    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("pass") String pass
    );
}
