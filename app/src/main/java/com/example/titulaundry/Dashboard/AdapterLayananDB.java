package com.example.titulaundry.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;

import java.util.ArrayList;

public class AdapterLayananDB extends RecyclerView.Adapter<AdapterLayananDB.myViewHolder> {
    private Context context;
    private ArrayList jenis_layanan , deskripsi_layanan,waktu_layanan,harga_layanan;
    String email;

    public AdapterLayananDB(Context context, ArrayList jenis_layanan, ArrayList deskripsi_layanan, ArrayList waktu_layanan, ArrayList harga_layanan,String email) {
        this.context = context;
        this.jenis_layanan = jenis_layanan;
        this.deskripsi_layanan = deskripsi_layanan;
        this.waktu_layanan = waktu_layanan;
        this.harga_layanan = harga_layanan;
        this.email = email;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layanan,parent,false);
        return new AdapterLayananDB.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {


        String lyn = String.valueOf(jenis_layanan.get(position));
        String desc = String.valueOf(deskripsi_layanan.get(position));
        String wak = String.valueOf(waktu_layanan.get(position));
        String hrg = String.valueOf(harga_layanan.get(position));

        holder.jenis_layanan.setText(lyn);
        holder.descLayanan.setText(desc);
        holder.waktuLayanan.setText(wak);
        holder.hargaLayanan.setText(hrg);
        holder.cardLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, pesanan.class);
                i.putExtra("layanan",lyn);
                i.putExtra("deskripsi",desc);
                i.putExtra("waktu",wak);
                i.putExtra("harga",hrg);
                i.putExtra("turu",hrg);
                i.putExtra("email",email);
                System.out.println("Emailnya adalah : "+email);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jenis_layanan.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView jenis_layanan , descLayanan , waktuLayanan , hargaLayanan;
        String turu;
        CardView cardLayanan;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            jenis_layanan = itemView.findViewById(R.id.inpo);
            descLayanan = itemView.findViewById(R.id.tulis1);
            waktuLayanan = itemView.findViewById(R.id.waktuLamaLayanan);
            hargaLayanan = itemView.findViewById(R.id.hargaLayanan);
            cardLayanan = itemView.findViewById(R.id.cuciKering);
        }
    }
}
