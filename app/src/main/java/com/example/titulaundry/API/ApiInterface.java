package com.example.titulaundry.API;

import com.example.titulaundry.Model.CheckEmail;
import com.example.titulaundry.Model.ResponeBarang;
import com.example.titulaundry.Model.ResponseAlamat;
import com.example.titulaundry.Model.ResponseEmail;
import com.example.titulaundry.Model.ResponseImg;
import com.example.titulaundry.Model.ResponseInsertPesanan;
import com.example.titulaundry.Model.ResponseLogin;
import com.example.titulaundry.Model.ResponsePesanan;
import com.example.titulaundry.Model.ResponseRegister;
import com.example.titulaundry.Model.ResponseUser;
import com.example.titulaundry.Model.SemuaPesanan;
import com.example.titulaundry.Model.UpdatePassword;
import com.example.titulaundry.Model.VerifEmail;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login2.php")
    Call<ResponseLogin> loginResponse(
            @Field("email") String email,
            @Field("password") String pass
    );
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> registerResponse(
            @Field("nama") String nama,
            @Field("no_telpon") String telp,
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("DataVerif_Email.php")
    Call<ResponseEmail> getVerifEmail(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("UpdateEmail.php")
    Call<VerifEmail> setUpdateEmail(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("CheckEmail.php")
    Call<CheckEmail> getCheckEmail(
            @Field("email") String gmail
    );
    @FormUrlEncoded
    @POST("LupaPassword.php")
    Call<UpdatePassword> setNewPassword(
            @Field("email") String gmail,
            @Field("password") String pass
    );
    @FormUrlEncoded
    @POST("SetAlamat.php")
    Call<ResponseAlamat> setAlamat(
            @Field("id_user") String user,
            @Field("alamat") String alamat
    );
    @GET("GetMenu.php")
    Call<ResponeBarang> getRetrive();

    @FormUrlEncoded
    @POST("GetPesanan.php")
    Call<ResponsePesanan> getPesanan(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("DataUser.php")
    Call<ResponseUser> getDataUser(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("InsertPesanan.php")
    Call<ResponseInsertPesanan> getDataPesanan(
            @Field("total_berat") String total_berat,
            @Field("total_harga") String total_harga,
            @Field("harga_diskon") String harga_diskon,
            @Field("waktu_penjemputan") String waktu_penjemputan,
            @Field("waktu_antar") String waktu_antar,
            @Field("id_voucher") String id_voucher,
            @Field("id_jasa") String id_jasa,
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("PesananSemua.php")
    Call<SemuaPesanan> getPesananSemua(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("PesananSaatIni.php")
    Call<SemuaPesanan> getPesananSaatIni(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("PesananSelesai.php")
    Call<SemuaPesanan> getPesananSelesai(
            @Field("id_user") String id_user
    );

    @Multipart
    @POST("UpImage.php")
    Call<ResponseImg> uploadImage (
            @Part MultipartBody.Part image,
            @Part("id_user")RequestBody idUser
            );
}
