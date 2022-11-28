package com.example.titulaundry.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.ModelMySQL.DataBarang;
import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.HolderData> {
    Context ctx;
    List<DataBarang> dataBarangList;

    public AdapterBarang(Context ctx, List<DataBarang> dataBarangList) {
        this.ctx = ctx;
        this.dataBarangList = dataBarangList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.grid_layanan,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder,  int position) {
        DataBarang db = dataBarangList.get(position);

        holder.jenis_layanan.setText(String.valueOf(dataBarangList.get(position).getJenis_jasa()));
        holder.descLayanan.setText(String.valueOf(db.getDeskripsi()));
        holder.waktuLayanan.setText(String.valueOf(db.getDurrasi()));
        holder.hargaLayanan.setText(String.valueOf(db.getHarga()));
        holder.id_jasa.setText(String.valueOf(db.getId_jasa()));
        Picasso.get().load(AppClient.URL_IMG+dataBarangList.get(position).getImage()).error(R.drawable.cuci_kering).into(holder.gmbr);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx,pesanan.class);
                i.putExtra("layanan",db.getJenis_jasa());
                i.putExtra("deskripsi",db.getDeskripsi());
                i.putExtra("waktu",db.getDurrasi());
                i.putExtra("harga",String.valueOf(db.getHarga()));
                i.putExtra("imagee",db.getImage());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBarangList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView jenis_layanan , descLayanan , waktuLayanan , hargaLayanan,id_jasa;
        ImageView gmbr;
        CardView cardView;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            jenis_layanan = itemView.findViewById(R.id.inpo);
            descLayanan = itemView.findViewById(R.id.tulis1);
            waktuLayanan = itemView.findViewById(R.id.waktuLamaLayanan);
            hargaLayanan = itemView.findViewById(R.id.hargaLayanan);
            id_jasa = itemView.findViewById(R.id.idJasa);
            gmbr = itemView.findViewById(R.id.imgInCard1);
            cardView = itemView.findViewById(R.id.cuciKering);

        }
    }
}
