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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Dashboard.MainMenu;
import com.example.titulaundry.Dashboard.home_fragment;
import com.example.titulaundry.Dashboard.order_fragment;
import com.example.titulaundry.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pesanan extends AppCompatActivity {
    ImageButton toDashboard;
    ImageView imgLayanan;
    Button gasPesanan;
    CardView setAlamat,setBeratCuci;
    TextView header , headerBawah,hargaCucian,lamaWaktu,setWaktualamat,setBeratCucian;
    public String layanan , desc , waktu , harga,Imgg;

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
        System.out.println("Ini turu adalah = "+getIntent().getStringExtra("turu"));
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
                i.putExtra("email",getIntent().getStringExtra("email"));

                //bawah data dari class waktuAlamat
                i.putExtra("hariJemput",getIntent().getStringExtra("hariJemput"));
                i.putExtra("alamatUserPick",getIntent().getStringExtra("alamatUserPick"));
                i.putExtra("alamatUserSend",getIntent().getStringExtra("alamatUserSend"));
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
            setBeratCucian.setText("Atur Berat Cucian");
        } else {
            setBeratCucian.setText("Berat : "+setBerat + " Kg");
        }
    }

    public void dataFromAlamat()  {

        setWaktualamat = (TextView) findViewById(R.id.inpowaktu);
        String setAlamat = getIntent().getStringExtra("hariJemput");
        System.out.println("Jika Sendiri" + setAlamat);

        if (setAlamat == null){
            setWaktualamat.setText("Pilih waktu jemput dan pengiriman");
            System.out.println("kenek 2");
        } else if(setAlamat.equals("Antar Sendiri")){
            setWaktualamat.setText(setAlamat);
        }else{
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
        imgLayanan = (ImageView) findViewById(R.id.imgMsg);

        layanan = getIntent().getStringExtra("layanan");
        waktu = getIntent().getStringExtra("waktu");
        harga = getIntent().getStringExtra("harga");
        Imgg = getIntent().getStringExtra("imagee");

        header.setText(layanan);
        headerBawah.setText(layanan);
        lamaWaktu.setText(waktu + " waktu pengerjaan");
        hargaCucian.setText(harga);
        Picasso.get().load(AppClient.URL_IMG+Imgg).error(R.drawable.cuci_kering).into(imgLayanan);

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
                i.putExtra("imagee",Imgg);
                i.putExtra("email",getIntent().getStringExtra("email"));
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
                String dayPick =getIntent().getStringExtra("hariJemput");
                String daySend = getIntent().getStringExtra("hariKembali");
                String timePick = getIntent().getStringExtra("waktuJemput");
                String timeSend = getIntent().getStringExtra("waktuKembali");
                String addressPick = getIntent().getStringExtra("alamatUserPick");
                String addressSend = getIntent().getStringExtra("alamatUserSend");
                String weight = getIntent().getStringExtra("berat");

                System.out.println("Jenis Pesanan = "+layanan + "" + getIntent().getStringExtra("berat")+" Kg");
                System.out.println("Penjemputan = " + getIntent().getStringExtra("hariJemput"));
                System.out.println("jam jemput = "+getIntent().getStringExtra("waktuJemput"));
                System.out.println("Pengiriman = "+getIntent().getStringExtra("hariKembali"));
                System.out.println("Jam pengiriman = "+getIntent().getStringExtra("waktuKembali"));
                System.out.println("Alamat = "+getIntent().getStringExtra("alamatUserPick"));
                System.out.println("Alamat = "+getIntent().getStringExtra("alamatUserSend"));
                System.out.println("harga Laundry = "+harga);
                System.out.println("total Berat = "+getIntent().getStringExtra("berat"));

                if (dayPick == null  || weight ==null ){
                    Toast.makeText(pesanan.this,"Isi Lengkap Terlebih dahulu",Toast.LENGTH_SHORT).show();

                } else {
                    Intent i = new Intent(getApplicationContext(),Detail_Pesanan.class);
                    i.putExtra("layanan",layanan);
                    i.putExtra("berat",weight);
                    i.putExtra("hariJemput",dayPick);
                    i.putExtra("waktuJemput",timePick);
                    i.putExtra("hariKembali",daySend);
                    i.putExtra("waktuKembali",timeSend);
                    i.putExtra("alamatUserPick",addressPick);
                    i.putExtra("alamatUserSend",addressSend);
                    i.putExtra("harga",harga);
                    i.putExtra("email",getIntent().getStringExtra("email"));
                    startActivity(i);
                }

            }
        });
    }
}