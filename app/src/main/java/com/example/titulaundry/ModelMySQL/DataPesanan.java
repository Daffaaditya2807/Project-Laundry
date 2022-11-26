package com.example.titulaundry.ModelMySQL;

import com.google.gson.annotations.SerializedName;

public class DataPesanan {

	@SerializedName("jenis_jasa")
	private String jenisJasa;

	@SerializedName("total_berat")
	private String totalBerat;

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("durasi")
	private String durasi;

	@SerializedName("status_pesanan")
	private String statusPesanan;

	public void setJenisJasa(String jenisJasa){
		this.jenisJasa = jenisJasa;
	}

	public String getJenisJasa(){
		return jenisJasa;
	}

	public void setTotalBerat(String totalBerat){
		this.totalBerat = totalBerat;
	}

	public String getTotalBerat(){
		return totalBerat;
	}

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setDurasi(String durasi){
		this.durasi = durasi;
	}

	public String getDurasi(){
		return durasi;
	}

	public void setStatusPesanan(String statusPesanan){
		this.statusPesanan = statusPesanan;
	}

	public String getStatusPesanan(){
		return statusPesanan;
	}
}