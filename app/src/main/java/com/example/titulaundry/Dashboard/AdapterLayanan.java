package com.example.titulaundry.Dashboard;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;

import java.util.ArrayList;

public class AdapterLayanan extends RecyclerView.Adapter<AdapterLayanan.ViewHolder>{
    ArrayList<model_item_layanan> dataItem;
    private Context context;



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jenis_layanan , descLayanan , waktuLayanan , hargaLayanan;
        LinearLayout lnrLayout;
        CardView cardLayanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jenis_layanan = itemView.findViewById(R.id.inpo);
            descLayanan = itemView.findViewById(R.id.tulis1);
            waktuLayanan = itemView.findViewById(R.id.waktuLamaLayanan);
            hargaLayanan = itemView.findViewById(R.id.hargaLayanan);
            lnrLayout = itemView.findViewById(R.id.lnrLayout);
            cardLayanan = itemView.findViewById(R.id.cuciKering);
        }
    }
    AdapterLayanan(ArrayList<model_item_layanan> data,Context context){
        this.context = context;
        this.dataItem = data;
    }

    @NonNull
    @Override
    public AdapterLayanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layanan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLayanan.ViewHolder holder, int position) {
        TextView jnsLyn = holder.jenis_layanan;
        TextView dscLyn = holder.descLayanan;
        TextView wktLyn = holder.waktuLayanan;
        TextView hrgLyn = holder.hargaLayanan;

        jnsLyn.setText(dataItem.get(position).getJenisLayanan());
        dscLyn.setText(dataItem.get(position).getDeskripsiLayanan());
        wktLyn.setText(dataItem.get(position).getWaktuLayanan());
        hrgLyn.setText("Rp."+dataItem.get(position).getHargaLayanan());
        holder.cardLayanan.setOnClickListener(v -> {
            Toast.makeText(context,"meki"+dataItem.get(position).getJenisLayanan(),Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, pesanan.class);
            i.putExtra("layanan",dataItem.get(position).getJenisLayanan());
            i.putExtra("deskripsi",dataItem.get(position).getDeskripsiLayanan());
            i.putExtra("waktu",dataItem.get(position).getWaktuLayanan());
            i.putExtra("harga",dataItem.get(position).getHargaLayanan());
            context.startActivity(i);
        });


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }


}
