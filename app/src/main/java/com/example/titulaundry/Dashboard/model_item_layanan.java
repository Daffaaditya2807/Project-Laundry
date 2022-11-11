package com.example.titulaundry.Dashboard;

public class model_item_layanan {
    String jenisLayanan , deskripsiLayanan , waktuLayanan , hargaLayanan;

    public model_item_layanan(String jenisLayanan, String deskripsiLayanan, String waktuLayanan, String hargaLayanan) {
        this.jenisLayanan = jenisLayanan;
        this.deskripsiLayanan = deskripsiLayanan;
        this.waktuLayanan = waktuLayanan;
        this.hargaLayanan = hargaLayanan;
    }

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public String getDeskripsiLayanan() {
        return deskripsiLayanan;
    }

    public String getWaktuLayanan() {
        return waktuLayanan;
    }

    public String getHargaLayanan() {
        return hargaLayanan;
    }
}
