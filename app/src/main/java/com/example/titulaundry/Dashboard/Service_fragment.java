package com.example.titulaundry.Dashboard;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Adapter.AdapterBarang;
import com.example.titulaundry.Model.ResponeBarang;
import com.example.titulaundry.ModelMySQL.DataBarang;
import com.example.titulaundry.R;
import com.example.titulaundry.layanan.Alert_App;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Service_fragment extends Fragment {
    RecyclerView.Adapter adData;
    RecyclerView recyclerView;
    //use DB MySQL
    AdapterBarang adapterBarang;
    private List<DataBarang> dataBarangList = new ArrayList<>();
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecycleMySQL();
        cariLayanan();

    }

    public void cariLayanan(){
        searchView = (SearchView) getView().findViewById(R.id.inputPencarian);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FilterList(s);
                return true;
            }
        });
    }

    public void FilterList(String text){

        List<DataBarang> FilteredList = new ArrayList<>();
        for (DataBarang brg : dataBarangList){
            if (brg.getJenis_jasa().toLowerCase().contains(text.toLowerCase())){

                FilteredList.add(brg);
            }
        }

        if (FilteredList.isEmpty()){
//            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
            Alert_App.alertBro(getContext(),"Data Tidak Tersedia");
        } else {

            adapterBarang.setFilteredList(FilteredList);
        }
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

                adapterBarang = new AdapterBarang(getContext(),dataBarangList,getActivity().getIntent());

                recyclerView = getView().findViewById(R.id.recycleLayanan);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

                recyclerView.setAdapter(adapterBarang);

                adapterBarang.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<ResponeBarang> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }



}