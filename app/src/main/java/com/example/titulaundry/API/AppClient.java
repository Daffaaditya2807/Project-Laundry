package com.example.titulaundry.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {
    public static String BASE_URL = "http:/172.16.109.239/API_LAUNDRY/";
    public static String URL_IMG = "http:/172.16.109.239/API_LAUNDRY/assets/";
    public static String profileIMG = "http:/172.16.109.239/API_LAUNDRY/image_profile/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;}
}
