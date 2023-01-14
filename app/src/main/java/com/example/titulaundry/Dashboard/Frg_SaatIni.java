package com.example.titulaundry.Dashboard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Adapter.AdapterPesananSemua;
import com.example.titulaundry.Model.SemuaPesanan;
import com.example.titulaundry.ModelMySQL.DataPesananSemua;
import com.example.titulaundry.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Frg_SaatIni extends Fragment {

    RecyclerView recyclerView;
    //use DB MySQL
    AdapterPesananSemua adapterPesanan1;
    private List<DataPesananSemua> pesananSemuaList = new ArrayList<>();
    TextView beratCuci,pesanan;
    ImageButton searchPesanan,refreshPesanan,pilih_mulai , pilih_akhir;
    EditText getTglMulai , getTglAkhir;
    DatePickerDialog pickerDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saat_ini, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        PesananSaatIni();
        cariPesananSaatIni();
        refresh();
    }

    public void PesananSaatIni(){
        beratCuci = getView().findViewById(R.id.todbrt);
        pesanan = getView().findViewById(R.id.todPes);
        AdapterPesananSemua.PassData passData = new AdapterPesananSemua.PassData() {
            @Override
            public void passData(int berat ,int hargaa){
                System.out.println(String.valueOf("Berat barang adalah = "+berat));
                beratCuci.setText(String.valueOf(berat)+ " Kg");

            }
        };
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREF_ACCOUNT", Context.MODE_PRIVATE);
        String id_user = sharedPreferences.getString("KEY_ID","");
        System.out.println(id_user);
        ApiInterface apiInterface = AppClient.getClient().create(ApiInterface.class);
        Call<SemuaPesanan> pesananCall = apiInterface.getPesananSaatIni(id_user);
        pesananCall.enqueue(new Callback<SemuaPesanan>() {
            @Override
            public void onResponse(Call<SemuaPesanan> call, Response<SemuaPesanan> response) {
                if (response.body().getKode()==1){
                    pesananSemuaList = response.body().getData();
                    adapterPesanan1 = new AdapterPesananSemua(getContext(),pesananSemuaList,passData);
                    recyclerView = getView().findViewById(R.id.recycleSemuaIni);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapterPesanan1);
                    int p = recyclerView.getAdapter().getItemCount();
                    pesanan.setText(String.valueOf(p));
                    adapterPesanan1.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Kosong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SemuaPesanan> call, Throwable t) {

            }
        });
    }
    public void refresh(){
        refreshPesanan = (ImageButton) getView().findViewById(R.id.refresh);
        refreshPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PesananSaatIni();
                getTglMulai.setText(null);
                getTglAkhir.setText(null);
            }
        });
    }
    public void cariPesananSaatIni(){
        searchPesanan = (ImageButton) getView().findViewById(R.id.cariPesanan);
        pilih_mulai = (ImageButton) getView().findViewById(R.id.pilih_mulai);
        pilih_akhir = (ImageButton) getView().findViewById(R.id.pilih_akhir);
        getTglMulai = (EditText) getView().findViewById(R.id.tgl_mulai);
        getTglAkhir = (EditText) getView().findViewById(R.id.tgl_akhir);
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        pilih_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String tgl1 = year + "-"+(month+1)+"-"+day;
                        getTglMulai.setText(tgl1);
                    }
                },year,month,day);
                pickerDialog.show();
            }
        });
        pilih_akhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String tgl1 = year + "-"+(month+1)+"-"+day;
                        getTglAkhir.setText(tgl1);
                    }
                },year,month,day);
                pickerDialog.show();
            }
        });
        searchPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdapterPesananSemua.PassData passData = new AdapterPesananSemua.PassData() {
                    @Override
                    public void passData(int berat ,int hargaa){
                        System.out.println(String.valueOf("Berat barang adalah = "+berat));
                        beratCuci.setText(String.valueOf(berat)+ " Kg");

                    }
                };
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREF_ACCOUNT", Context.MODE_PRIVATE);
                String id_user = sharedPreferences.getString("KEY_ID","");
                System.out.println(id_user);
                ApiInterface apiInterface = AppClient.getClient().create(ApiInterface.class);
                Call<SemuaPesanan> pesananCall = apiInterface.getCariPesananSaatIni(id_user,getTglMulai.getText().toString(),getTglAkhir.getText().toString());
                pesananCall.enqueue(new Callback<SemuaPesanan>() {
                    @Override
                    public void onResponse(Call<SemuaPesanan> call, Response<SemuaPesanan> response) {
                        if (response.body().getKode()==1){
                            pesananSemuaList = response.body().getData();
                            adapterPesanan1 = new AdapterPesananSemua(getContext(),pesananSemuaList,passData);
                            recyclerView = getView().findViewById(R.id.recycleSemuaIni);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adapterPesanan1);
                            int p = recyclerView.getAdapter().getItemCount();
                            pesanan.setText(String.valueOf(p));
                            adapterPesanan1.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Kosong", Toast.LENGTH_SHORT).show();
                            adapterPesanan1.clear();
                            beratCuci.setText("0 Kg");
                            pesanan.setText("0");
                        }

                    }

                    @Override
                    public void onFailure(Call<SemuaPesanan> call, Throwable t) {

                    }
                });
            }
        });


    }
}