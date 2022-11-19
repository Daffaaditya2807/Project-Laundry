package com.example.titulaundry.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titulaundry.R;

import java.util.ArrayList;

public class AdapterPesananDB extends RecyclerView.Adapter<AdapterPesananDB.ViewHolder> {
    private Context context;
    private ArrayList JasaPesanan,berat_cucian,status,waktu,total_harga;

    public AdapterPesananDB(Context context,  ArrayList jasa,  ArrayList status, ArrayList waktu, ArrayList total_harga) {
        this.context = context;
        this.JasaPesanan = jasa;
        this.status = status;
        this.waktu = waktu;
        this.total_harga = total_harga;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesanan_user,parent,false);
        return new AdapterPesananDB.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String js = String.valueOf(JasaPesanan.get(position));
        String sts = String.valueOf(status.get(position));
        String wkt = String.valueOf(waktu.get(position));
        String todHarga = String.valueOf(total_harga.get(position));

        holder.jasa.setText(js);
        holder.status.setText(sts);
        holder.waktuEst.setText(wkt);
        holder.totalHarga.setText(todHarga);
    }

    @Override
    public int getItemCount() {
        return JasaPesanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jasa , status,waktuEst,totalHarga;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jasa = itemView.findViewById(R.id.jasa);
            status = itemView.findViewById(R.id.status);
            waktuEst = itemView.findViewById(R.id.waktuEst);
            totalHarga = itemView.findViewById(R.id.totalHarga);
        }
    }
}
