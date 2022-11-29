package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.TextView;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Dashboard.MainMenu;
import com.example.titulaundry.Dashboard.home_fragment;
import com.example.titulaundry.Model.ResponseInsertPesanan;
import com.example.titulaundry.R;
//import com.example.titulaundry.db_help.Database_Tb_Pesanan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Pesanan extends AppCompatActivity {

    String layanan , hargaLayanan , beratCucian,hariJemput,hariKirim,waktuJemput,waktuKirim,alamatUserPick,alamatUserSend,idOrg,IdJasa,idVoucher;
    TextView jnslyn,txHariJemput,txHariKirim,beratXharga,totalHarga,alamatDetailPick,alamatDetailSend;
    String formatHariJemput,formatHariKirim;
    int GettotalHarga;
    Button backToHome;
    ConstraintLayout lyt;
    ApiInterface apiInterface;

//
//    Database_Tb_Pesanan PS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        notif(Detail_Pesanan.this);
        setLayoutPesanan();
        getDataFromPesanan();
        setFormatHari();
        setDataFromPesanan();

        setAlamat();

//        InsertPesanan();


    }

    public void setLayoutPesanan(){
        lyt = (ConstraintLayout) findViewById(R.id.menuKurir);

        String layout = getIntent().getStringExtra("hariJemput");
        if (layout.equals("Antar Sendiri")){
            lyt.setVisibility(View.GONE);
        } else {

        }

    }

    public void setAlamat(){
        alamatDetailPick = (TextView) findViewById(R.id.alamatDetail);
        alamatDetailSend = (TextView) findViewById(R.id.alamatDetailKirim);
        alamatDetailPick.setText(alamatUserPick);
        alamatDetailSend.setText(alamatUserSend);
    }

    public static int HitungBro(String hargaLayanan , String beratCucian){
        String fm = hargaLayanan;
        fm = fm.replaceAll("[^\\d.]", "");
        fm = fm.replace(".","");
        int harga = Integer.parseInt(fm);
        int berat = Integer.parseInt(beratCucian);
        int ppn = 1320;
        return (harga*berat)+ppn;
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
        beratXharga.setText(String.valueOf(hargaLayanan)+" X "+String.valueOf(beratCucian)+" Kg");

        int hargaa = HitungBro(hargaLayanan,beratCucian);
        //total
        totalHarga = (TextView) findViewById(R.id.hargaFix);
        totalHarga.setText("Rp. "+String.valueOf(hargaa));
    }

    public void setFormatHari(){
        if (!hariJemput.equals("Antar Sendiri")||!hariKirim.equals("Antar Sendiri")){
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat format3 = new SimpleDateFormat("EEEE");
            SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = format1.parse(hariJemput);
                Date date1 = format1.parse(hariKirim);
                formatHariJemput = format3.format(date);
                formatHariKirim = format3.format(date1);
                hariJemput = format4.format(date);
                hariKirim = format4.format(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
        alamatUserPick = getIntent().getStringExtra("alamatUserPick");
        alamatUserSend = getIntent().getStringExtra("alamatUserSend");
        idOrg = getIntent().getStringExtra("id_user");
        IdJasa = getIntent().getStringExtra("id_jasa");


        backToHome = (Button) findViewById(R.id.buatPesanan);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //harga
                int hargaa2 = HitungBro(hargaLayanan,beratCucian);

                if (waktuJemput == null || waktuKirim == null){
                    waktuJemput = "";
                    waktuKirim = "";
                }
                //waktuJemput
                String pickAku = hariJemput+" "+waktuJemput;
                String SendAku = hariKirim+" "+waktuKirim;

                System.out.println("Id User = "+idOrg);
                System.out.println("Id Jasa = "+IdJasa);
                System.out.println("total_berat = "+beratCucian);
                System.out.println("total_harga = "+hargaa2);
                System.out.println("Waktu Penjemputan = "+hariJemput);
                System.out.println("Waktu Kirim = "+hariKirim);


                System.out.println("Saat ada tanggal e "+pickAku);
                System.out.println("Saat ada tanggal e  "+SendAku);

                //Insert Data HERE
                apiInterface = AppClient.getClient().create(ApiInterface.class);
                Call<ResponseInsertPesanan> insertPesananCall = apiInterface.getDataPesanan(beratCucian,
                        String.valueOf(hargaa2),String.valueOf(hargaa2),pickAku,SendAku,"1",IdJasa,idOrg);
                insertPesananCall.enqueue(new Callback<ResponseInsertPesanan>() {
                    @Override
                    public void onResponse(Call<ResponseInsertPesanan> call, Response<ResponseInsertPesanan> response) {
                        int kode = response.body().getKode();
                        System.out.println("APAKAH MASUK "+kode);
                        Intent i = new Intent(getApplicationContext(),MainMenu.class);
                        i.putExtra("id_user",idOrg);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseInsertPesanan> call, Throwable t) {

                    }
                });

            }
        });

    }

//    public void InsertPesanan(){
//
//        backToHome = (Button) findViewById(R.id.buatPesanan);
//        backToHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int min = 1;
//                int max = 10000;
//
//                //Generate random int value from 50 to 100
//                System.out.println("Random value in int from "+min+" to "+max+ ":");
//                int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
//                System.out.println(random_int);
//                String numberRandom = String.valueOf(random_int);
//                Intent i = new Intent(getApplicationContext(), MainMenu.class);
//                i.putExtra("id_user",getIntent().getStringExtra("id_user"));
//                System.out.println("ID USER TO MAIN MENU == "+getIntent().getStringExtra("id_user"));
//                startActivity(i);
//                finish();
//            }
//        });

  //  }
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