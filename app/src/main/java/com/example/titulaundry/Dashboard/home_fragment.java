package com.example.titulaundry.Dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;
import com.example.titulaundry.db_help.Database_Tb_Pesanan;
import com.example.titulaundry.db_help.Database_Tb_user;
import com.example.titulaundry.db_help.Database_Tb_jasa;

import java.util.ArrayList;
import java.util.Calendar;


public class home_fragment extends Fragment {
    CardView toDaftar;
    public TextView greeting;
    public Database_Tb_user dbHelper;
    Cursor cursor;
    String waktu;

    RecyclerView recyclerView;

    //use sample
    AdapterLayanan adapterLayanan;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<model_item_layanan> data;

    //use DB
    AdapterLayananDB adapterLayananDB;
    Database_Tb_jasa DB;
    ArrayList<String> jenis , deskripsi , waktuLayanan , harga;

    //use DB pesanan
    AdapterPesananDB adapterPesananDB;
    Database_Tb_Pesanan PS;
    ArrayList<String> JenisPesanan , statusPesanan,WaktuPesanan,TotalPesanan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        setGreeting();
        RecycleDB();
        RecycleDBPesanan();
//      RecycleLayanan();

    }
    public void RecycleDBPesanan(){
        PS = new Database_Tb_Pesanan(getContext());
        JenisPesanan = new ArrayList<>();
        statusPesanan = new ArrayList<>();
        WaktuPesanan = new ArrayList<>();
        TotalPesanan = new ArrayList<>();
        recyclerView = getView().findViewById(R.id.recyclePesanan);
        adapterPesananDB = new AdapterPesananDB(getContext(),JenisPesanan,statusPesanan,WaktuPesanan,TotalPesanan);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPesananDB);;
        displayDataPesanan();
    }

    private void displayDataPesanan() {
        Cursor cursor = PS.getDataPesanan();
        if (cursor.getCount()==0){
            Toast.makeText(getContext(), "KOSONGG", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                JenisPesanan.add(cursor.getString(1)+" "+cursor.getString(2)+" Kg");
                statusPesanan.add(cursor.getString(3));
                WaktuPesanan.add(cursor.getString(4));
                TotalPesanan.add("Rp. "+cursor.getString(5));
                System.out.println("Berat Cucian = "+cursor.getString(2));
            }
        }
    }

    public void RecycleDB(){
        DB = new Database_Tb_jasa(getContext());
        jenis = new ArrayList<>();
        deskripsi = new ArrayList<>();
        waktuLayanan = new ArrayList<>();
        harga = new ArrayList<>();
        String email = getActivity().getIntent().getStringExtra("email");
        recyclerView = getView().findViewById(R.id.recycleLayanan);
        adapterLayananDB = new AdapterLayananDB(getContext(),jenis,deskripsi,waktuLayanan,harga,email);
        //insert layanan
//        DB.inserDataLayanan("js200","Cuci Uap","Mencuci baju dengan uap panas","2 hari" ,"3000");
//        DB.inserDataLayanan("js201","Cuci Tidak Basah","Mencuci baju dengan Tanpa air","1 hari" ,"7000");
//        DB.inserDataLayanan("js202","Cuci Mandiri","Mencuci baju Sendiri di Tokoh kami","1 hari" ,"12000");
//        DB.inserDataLayanan("js204","Cuci Jual","Mencuci baju Kemudian dijual","7 hari" ,"2500");
//        DB.inserDataLayanan("js205","Cuci Hilang","Setelah di cuci baju Hilang","30 hari" ,"500");
//        DB.inserDataLayanan("js207","Jual Mesin cuci","Spek Bore Up std Tiger","1 hari" ,"2500000");
        //======================
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterLayananDB);
        displayData();

    }

    private void displayData() {
        Cursor cursor = DB.getDataLayanan();
        if (cursor.getCount()==0){
            Toast.makeText(getContext(),"Hai dek",Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                jenis.add(cursor.getString(1));
                deskripsi.add(cursor.getString(2));
                waktuLayanan.add(cursor.getString(3));
                harga.add("Rp. "+cursor.getString(4));
            }
        }
    }

    public void RecycleLayanan(){
        recyclerView = getView().findViewById(R.id.recycleLayanan);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i< item_layanan.jenis_layanan.length;i++){
                data.add(new model_item_layanan(
                        item_layanan.jenis_layanan[i],
                        item_layanan.deskripsi_layanan[i],
                        item_layanan.waktu_layanan[i],
                        item_layanan.harga_layanan[i]
                ));
        }
        adapterLayanan = new AdapterLayanan(data,getContext());
        recyclerView.setAdapter(adapterLayanan);

    }
    public void setGreeting(){
        //set waktu
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour <= 6 || hour <= 11) {
            waktu = " pagi ";
        } else if (hour <= 17) {
            waktu = " Siang  ";
        } else if (hour <= 24) {
            waktu = " Malam " ;
        }

        greeting = (TextView) getView().findViewById(R.id.greeting);
        dbHelper = new Database_Tb_user(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT nama FROM user WHERE email = '"+getActivity().getIntent().getStringExtra("email")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            greeting.setText("Selamat"+waktu+""+cursor.getString(0).toString());
        }

    }

}