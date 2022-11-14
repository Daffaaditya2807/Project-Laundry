package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.titulaundry.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Detail_Pesanan extends AppCompatActivity {

    String layanan , hargaLayanan , beratCucian,hariJemput,hariKirim,waktuJemput,waktuKirim,alamatUser;
    TextView jnslyn,txHariJemput,txHariKirim,txJamJemput,txJamKirim,beratXharga,totalHarga;
    String formatHariJemput,formatHariKirim;
    int GettotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        notif(Detail_Pesanan.this);

        getDataFromPesanan();
        HitungTotal();
        setFormatHari();
        setDataFromPesanan();


    }

    public void HitungTotal(){
        String fm = hargaLayanan;
        fm = fm.replaceAll("[^\\d.]", "");
        fm = fm.replace(".","");
        int harga = Integer.parseInt(fm);
        int berat = Integer.parseInt(beratCucian);
        int ppn = 1320;
        GettotalHarga = (harga*berat)+ppn;
    }

    public void setDataFromPesanan(){
        //layanan
        jnslyn = (TextView) findViewById(R.id.jenisLayanan);
        jnslyn.setText("Layanan " + layanan + " "+ beratCucian + " Kg");

        //hari Jemput
        txHariJemput = (TextView) findViewById(R.id.jamJemput);
        txHariJemput.setText(formatHariJemput+"-"+waktuJemput+" WIB");

        //hari Kirim
        txHariKirim = (TextView) findViewById(R.id.jamKirim);
        txHariKirim.setText(formatHariKirim+"-"+waktuKirim+" WIB");

        //harga x berat
        beratXharga = (TextView) findViewById(R.id.totalBeratCucian);
        beratXharga.setText("Rp. "+hargaLayanan+"X"+beratCucian+" Kg");

        //total
        totalHarga = (TextView) findViewById(R.id.hargaFix);
        totalHarga.setText("Rp. "+GettotalHarga);
    }

    public void setFormatHari(){
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat format3 = new SimpleDateFormat("EEEE");

        try {
            Date date = format1.parse(hariJemput);
            Date date1 = format1.parse(hariKirim);
            formatHariJemput = format3.format(date);
            formatHariKirim = format3.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    public void getDataFromPesanan(){
        layanan = getIntent().getStringExtra("layanan");
        hargaLayanan = getIntent().getStringExtra("harga");
        beratCucian = getIntent().getStringExtra("berat");
        hariJemput = getIntent().getStringExtra("hariJemput");
        hariKirim = getIntent().getStringExtra("hariKembali");
        waktuJemput = getIntent().getStringExtra("waktuJemput");
        waktuKirim = getIntent().getStringExtra("waktuKembali");
        alamatUser = getIntent().getStringExtra("alamatUser");
    }
    public void notif(Activity activity){
        //change color notif bar
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.white));
        //set icons notifbar
        View decor = activity.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}