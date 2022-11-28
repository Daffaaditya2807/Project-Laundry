package com.example.titulaundry.ModelMySQL;

import com.google.gson.annotations.SerializedName;

public class DataItemUser {

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("no_telpon")
	private String noTelpon;

	@SerializedName("email")
	private String email;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("profile_img")
	private String profile_img;

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setNoTelpon(String noTelpon){
		this.noTelpon = noTelpon;
	}

	public String getNoTelpon(){
		return noTelpon;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}