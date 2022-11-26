package com.example.titulaundry.Dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Adapter.AdapterBarang;
import com.example.titulaundry.Adapter.AdapterPesanan;
import com.example.titulaundry.Model.ResponeBarang;
import com.example.titulaundry.Model.ResponsePesanan;
import com.example.titulaundry.ModelMySQL.DataBarang;
import com.example.titulaundry.ModelMySQL.DataPesanan;
import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class home_fragment extends Fragment {
    public TextView greeting;
    String waktu;

    RecyclerView recyclerView;
    //use DB MySQL
    RecyclerView.Adapter adData;
    private List<DataBarang> dataBarangList = new ArrayList<>();
    private List<DataPesanan> pesananList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecycleMySQL();
        RecycleMySQLPesanan();


    }

    private void RecycleMySQLPesanan() {
        String id_user = getActivity().getIntent().getStringExtra("id_user");
        System.out.println("ID USER PADA HOME == "+id_user);
        ApiInterface apiInterface = AppClient.getClient().create(ApiInterface.class);
        Call<ResponsePesanan> responsePesananCall = apiInterface.getPesanan(id_user);
        responsePesananCall.enqueue(new Callback<ResponsePesanan>() {
            @Override
            public void onResponse(Call<ResponsePesanan> call, Response<ResponsePesanan> response) {
                if (response.body().getKode()==1){
                    String dataAda = response.body().getPesan();
                    System.out.println("Apakah Data ada ="+dataAda);
                    pesananList = response.body().getData();

                    adData = new AdapterPesanan(getContext(),pesananList);
                    recyclerView = getView().findViewById(R.id.recyclePesanan);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    recyclerView.setAdapter(adData);
                    adData.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "pesananKosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePesanan> call, Throwable t) {

            }
        });
    }

    public void setGreeting(){
        //set waktu
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour <= 6 || hour <= 11) {
            waktu = " pagi ";
        } else if (hour <= 17) {
            waktu = " Siang  ";
        } else if (hour <= 24) {
            waktu = " Malam " ;
        }

//        greeting = (TextView) getView().findViewById(R.id.greeting);
//        dbHelper = new Database_Tb_user(getActivity());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT nama FROM user WHERE email = '"+getActivity().getIntent().getStringExtra("email")+"'",null);
//        cursor.moveToFirst();
//        if (cursor.getCount()>0){
//            cursor.moveToPosition(0);
//            greeting.setText("Selamat"+waktu+""+cursor.getString(0).toString());
//        }

    }

    public void RecycleMySQL(){


        ApiInterface apiInterface = AppClient.getClient().create(ApiInterface.class);
        Call<ResponeBarang> GetData = apiInterface.getRetrive();
        GetData.enqueue(new Callback<ResponeBarang>() {
            @Override
            public void onResponse(Call<ResponeBarang> call, Response<ResponeBarang> response) {
                int kode = response.body().getKode();
                String Pesan = response.body().getPesan();

                dataBarangList = response.body().getData();

                adData = new AdapterBarang(getContext(),dataBarangList);

                recyclerView = getView().findViewById(R.id.recycleLayanan);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

                recyclerView.setAdapter(adData);
                adData.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<ResponeBarang> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}