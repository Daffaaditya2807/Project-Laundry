package com.example.titulaundry.Model;

import com.google.gson.annotations.SerializedName;

public class ResponeRegister{

	@SerializedName("kode")
	private int kode;

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}