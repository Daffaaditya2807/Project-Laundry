package com.example.titulaundry.Dashboard;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.titulaundry.R;
import com.example.titulaundry.db_help.Database_Tb_jasa;

import java.util.ArrayList;

public class Service_fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterLayananDB adapterLayananDB;
    Database_Tb_jasa DB;
    ArrayList<String> jenis , deskripsi , waktuLayanan , harga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecycleDB();

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


}