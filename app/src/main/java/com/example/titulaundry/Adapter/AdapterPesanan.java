package com.example.titulaundry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.ModelMySQL.DataPesanan;
import com.example.titulaundry.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
        holder.totalHarga.setText(String.valueOf(toRupiah(pesanan.getTotalHarga())));
        holder.waktuEst.setText(String.valueOf(pesanan.getDurasi()));
        Picasso.get().load(AppClient.URL_IMG+pesananList.get(position).getImage()).error(R.drawable.meki).into(holder.imageView);

    }
    public static String toRupiah(int rupiah){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(rupiah).replace(".00","");
    }

    @Override
    public int getItemCount() {
        return pesananList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jasa , status,waktuEst,totalHarga;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            jasa = itemView.findViewById(R.id.jasa);
            status = itemView.findViewById(R.id.status);
            waktuEst = itemView.findViewById(R.id.waktuEst);
            totalHarga = itemView.findViewById(R.id.totalHarga);
            imageView = itemView.findViewById(R.id.img1);
        }
    }
}
