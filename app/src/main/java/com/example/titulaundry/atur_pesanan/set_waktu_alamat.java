package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.titulaundry.R;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class set_waktu_alamat extends AppCompatActivity {
    RadioButton rBtn1 , rBtn2;
    DatePickerDialog picker;
    ConstraintLayout viewMenu;
    TextView tgl1 , tgl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_waktu_alamat);
        notif(set_waktu_alamat.this);
        PilihTanggal();
        checkedButton();
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

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    public void PilihTanggal(){
        tgl1 = (TextView) findViewById(R.id.TanggalJemput1);
        tgl2 = (TextView) findViewById(R.id.TanggalJemput2);

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        tgl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker = new DatePickerDialog(set_waktu_alamat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month ,int day) {


                        tgl1.setText(String.valueOf(day) + " " + getMonth((month+1)) + " " + String.valueOf(year));
                    }
                },year,month,day);
                picker.show();
            }
        });
        tgl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker = new DatePickerDialog(set_waktu_alamat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month ,int day) {


                        tgl2.setText(String.valueOf(day) + " " + getMonth((month+1)) + " " + String.valueOf(year));
                    }
                },year,month,day);
                picker.show();
            }
        });
    }

    public void checkedButton(){
        rBtn1 = (RadioButton) findViewById(R.id.radioButton1);
        rBtn2 = (RadioButton) findViewById(R.id.radioButton2);
        viewMenu = (ConstraintLayout) findViewById(R.id.kotakMenu);
        rBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rBtn1.isChecked()){
                    rBtn2.setChecked(false);
                    rBtn1.setChecked(true);
                    viewMenu.setVisibility(view.VISIBLE);
                }
            }
        });

        rBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rBtn1.setChecked(false);
                rBtn2.setChecked(true);
            }
        });
    }
}