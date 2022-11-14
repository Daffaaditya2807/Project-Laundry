package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.titulaundry.Dashboard.MainMenu;
import com.example.titulaundry.Dashboard.home_fragment;
import com.example.titulaundry.Dashboard.order_fragment;
import com.example.titulaundry.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pesanan extends AppCompatActivity {
    ImageButton toDashboard;
    Button gasPesanan;
    CardView setAlamat,setBeratCuci;
    TextView header , headerBawah,hargaCucian,lamaWaktu,setWaktualamat,setBeratCucian;
    public String layanan , desc , waktu , harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        notif(pesanan.this);
        toKembali();
        setAlamat();
        setPesanan();
        setBeratCucuian();
        dataFromAlamat();
        dataFromBerat();
        getPesanan();
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
    public void setBeratCucuian(){
        setBeratCuci = (CardView) findViewById(R.id.menu2);
        setBeratCuci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BeratCucian.class);
                //bawah data dari class pesanan
                i.putExtra("hargaLaundry",String.valueOf(harga));
                i.putExtra("layanan",layanan);
                i.putExtra("waktu",waktu);
                i.putExtra("harga",harga);

                //bawah data dari class waktuAlamat
                i.putExtra("hariJemput",getIntent().getStringExtra("hariJemput"));
                i.putExtra("alamatUser",getIntent().getStringExtra("alamatUser"));
                i.putExtra("hariKembali",getIntent().getStringExtra("hariKembali"));
                i.putExtra("waktuJemput",getIntent().getStringExtra("waktuJemput"));
                i.putExtra("waktuKembali",getIntent().getStringExtra("waktuKembali"));
                startActivity(i);
            }
        });

    }

    public void dataFromBerat(){
        setBeratCucian = (TextView) findViewById(R.id.beartCucian);
        String setBerat = getIntent().getStringExtra("berat");

        if (setBerat == null){
            setBeratCucian.setText("Halo dek");
        } else {
            setBeratCucian.setText("Berat : "+setBerat);
        }
    }

    public void dataFromAlamat()  {

        setWaktualamat = (TextView) findViewById(R.id.inpowaktu);
        String setAlamat = getIntent().getStringExtra("hariJemput");

        if (setAlamat == null){
            setWaktualamat.setText("Pilih waktu jemput dan pengiriman");
            System.out.println("kenek 2");
        } else{
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM yyyy");
            try {
                Date date = format1.parse(setAlamat);
                setWaktualamat.setText("Penjemputan : "+format2.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }
    public void setPesanan(){
        header = (TextView) findViewById(R.id.header);
        headerBawah = (TextView) findViewById(R.id.headerbawah);
        lamaWaktu = (TextView) findViewById(R.id.keterangan);
        hargaCucian = (TextView) findViewById(R.id.totalBerat);

        layanan = getIntent().getStringExtra("layanan");
        waktu = getIntent().getStringExtra("waktu");
        harga = getIntent().getStringExtra("harga");

        header.setText(layanan);
        headerBawah.setText(layanan);
        lamaWaktu.setText(waktu + " waktu pengerjaan");
        hargaCucian.setText(harga);

    }
    public void setAlamat(){
        setAlamat = (CardView) findViewById(R.id.menu1);
        setAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),set_waktu_alamat.class);
                //bawah data dari class pesanan
                i.putExtra("layanan",layanan);
                i.putExtra("waktu",waktu);
                i.putExtra("harga",harga);
                //bawah data dari class beratcucian
                i.putExtra("berat",getIntent().getStringExtra("berat"));
                startActivity(i);
            }
        });
    }
    public void toKembali(){
        toDashboard = (ImageButton) findViewById(R.id.kembali);
        toDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesanan.super.onBackPressed();
            }
        });
    }

    public void getPesanan(){
        gasPesanan = (Button) findViewById(R.id.buatPesanan) ;
        gasPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Jenis Pesanan = "+layanan + "" + getIntent().getStringExtra("berat")+" Kg");
                System.out.println("Penjemputan = " + getIntent().getStringExtra("hariJemput"));
                System.out.println("jam jemput = "+getIntent().getStringExtra("waktuJemput"));
                System.out.println("Pengiriman = "+getIntent().getStringExtra("hariKembali"));
                System.out.println("Jam pengiriman = "+getIntent().getStringExtra("waktuKembali"));
                System.out.println("Alamat = "+getIntent().getStringExtra("alamatUser"));
                System.out.println("harga Laundry = "+harga);
                System.out.println("total Berat = "+getIntent().getStringExtra("berat"));

                Intent i = new Intent(getApplicationContext(),Detail_Pesanan.class);
                i.putExtra("layanan",layanan);
                i.putExtra("berat",getIntent().getStringExtra("berat"));
                i.putExtra("hariJemput",getIntent().getStringExtra("hariJemput"));
                i.putExtra("waktuJemput",getIntent().getStringExtra("waktuJemput"));
                i.putExtra("hariKembali",getIntent().getStringExtra("hariKembali"));
                i.putExtra("waktuKembali",getIntent().getStringExtra("waktuKembali"));
                i.putExtra("alamatUser",getIntent().getStringExtra("alamatUser"));
                i.putExtra("harga",harga);
                i.putExtra("berat",getIntent().getStringExtra("berat"));
                startActivity(i);
            }
        });
    }
}