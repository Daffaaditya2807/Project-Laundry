package com.example.titulaundry.Dashboard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.titulaundry.R;
import com.example.titulaundry.atur_pesanan.pesanan;
import com.example.titulaundry.db_help.database;

import java.util.Calendar;


public class home_fragment extends Fragment {
    CardView toDaftar;
    public TextView greeting;
    public database dbHelper;
    Cursor cursor;
    String waktu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        buatPesanan();
        setGreeting();


    }
    public void setGreeting(){
        //set waktu
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour <= 6 || hour <= 12) {
            waktu = " pagi ";
        } else if (hour <= 17) {
            waktu = " Siang  ";
        } else if (hour <= 24) {
            waktu = " Malam " ;
        }
        greeting = (TextView) getView().findViewById(R.id.greeting);
        dbHelper = new database(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT nama FROM user WHERE email = '"+getActivity().getIntent().getStringExtra("email")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            greeting.setText("Selamat"+waktu+""+cursor.getString(0).toString());
        }
//        greeting.setText("Halo dek" + tes);
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