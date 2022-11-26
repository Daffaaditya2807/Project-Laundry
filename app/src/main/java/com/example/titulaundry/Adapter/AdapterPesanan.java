package com.example.titulaundry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titulaundry.ModelMySQL.DataPesanan;
import com.example.titulaundry.R;

import java.util.List;

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.ViewHolder> {
    Context ctx;
    List<DataPesanan> pesananList;

    public AdapterPesanan(Context ctx, List<DataPesanan> pesananList) {
        this.ctx = ctx;
        this.pesananList = pesananList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesanan_user,parent,false);
        return new AdapterPesanan.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataPesanan pesanan = pesananList.get(position);

        holder.jasa.setText(String.valueOf(pesanan.getJenisJasa()+" "+pesanan.getTotalBerat()+" Kg"));
        holder.status.setText(String.valueOf(pesanan.getStatusPesanan()));
        holder.totalHarga.setText(String.valueOf(pesanan.getTotalHarga()));
        holder.waktuEst.setText(String.valueOf(pesanan.getDurasi()));

    }

    @Override
    public int getItemCount() {
        return pesananList.size();
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
