package com.example.titulaundry.Dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;


public class home_fragment extends Fragment {
    CardView toDaftar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        buatPesanan();
    }

    public void buatPesanan(){
        toDaftar = (CardView) getView().findViewById(R.id.cuciKering);
        toDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), pesanan.class);
                startActivity(i);
            }
        });
    }
}