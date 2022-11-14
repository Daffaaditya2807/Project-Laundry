package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.titulaundry.R;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class set_waktu_alamat extends AppCompatActivity {
    RadioButton rBtn1 , rBtn2;
    ImageButton bckToPesanan;
    Button makePesanan;
    DatePickerDialog picker;
    ConstraintLayout viewMenu;
    TextView tgl1 , tgl2,jam1 ,jam2,alamatDetail;
    int hour , minute;

    String waktuJemput , waktuPengembalian,hariJemput,hariKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_waktu_alamat);
        notif(set_waktu_alamat.this);
        PilihTanggal();
        setBckToPesanan();
        checkedButton();
        PickerTime();
        bawahDataToPesanan();
    }
    public void setBckToPesanan(){
        bckToPesanan = (ImageButton) findViewById(R.id.kembali);
        bckToPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),pesanan.class);
//                startActivity(i);
                set_waktu_alamat.super.onBackPressed();
            }
        });
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
                        hariJemput = String.valueOf(day+"-"+(month+1)+"-"+year);
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
                        hariKembali = String.valueOf(day+"-"+(month+1)+"-"+year);
                    }
                },year,month,day);
                picker.show();
            }
        });
    }

    public void checkedButton(){
        rBtn1 = (RadioButton) findViewById(R.id.radioButton1);
        rBtn2 = (RadioButton) findViewById(R.id.radioButton2);
        makePesanan = (Button)findViewById(R.id.buatPesanan);

        viewMenu = (ConstraintLayout) findViewById(R.id.kotakMenu);
        rBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rBtn1.isChecked()){
                    rBtn2.setChecked(false);
                    rBtn1.setChecked(true);
                    makePesanan.setVisibility(view.VISIBLE);
                    viewMenu.setVisibility(view.VISIBLE);
                    jam1.setText("07:00 WIB");
                    jam2.setText("07:00 WIB");
                }
            }
        });

        rBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rBtn1.setChecked(false);
                rBtn2.setChecked(true);
                makePesanan.setVisibility(view.VISIBLE);
                viewMenu.setVisibility(view.VISIBLE);
                jam2.setText("07:00 WIB");
                jam1.setText("07:00 WIB");
            }
        });
    }

    public void setInterval(TimePickerDialog timePicker){
        try {
            NumberPicker minutePicker = timePicker.findViewById(Resources.getSystem().getIdentifier("minute","id","android"));
            String[] display = new String[]{"0","30"};

            minutePicker.setMinValue(0);
            minutePicker.setMaxValue(display.length-1);
            minutePicker.setDisplayedValues(display);

        }catch (Exception e){
            Log.d("Error : ",e.getMessage());
        }
    }

    public void PickerTime() {
        jam1 = (TextView) findViewById(R.id.jam1);
        jam2 = (TextView) findViewById(R.id.jam2);
        //waktu jemput
        jam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        System.out.println(String.valueOf(hour));
                        if (rBtn1.isChecked()){
                            if (hour>21 || hour<=7){
                                Toast.makeText(set_waktu_alamat.this,"Gaboleh",Toast.LENGTH_LONG).show();
                            } else {
                                jam1.setText(String.format(Locale.getDefault(),"%02d : %02d WIB",hour,minute));
                                waktuJemput = String.format(Locale.getDefault(),"%02d:%02d",hour,minute);

                            }
                        } else if (rBtn2.isChecked()){
                            if (hour>15 || hour<7){
                                Toast.makeText(set_waktu_alamat.this,"Gaboleh",Toast.LENGTH_LONG).show();
                            } else {
                                jam1.setText(String.format(Locale.getDefault(),"%02d : %02d WIB",hour,minute));
                                waktuJemput = String.format(Locale.getDefault(),"%02d:%02d",hour,minute);
                            }
                        } else {
                            Toast.makeText(set_waktu_alamat.this,"Mohon checklist dulu",Toast.LENGTH_LONG).show();
                        }
                    }
                };
                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(set_waktu_alamat.this,style,onTimeSetListener,hour,minute,true);
                setInterval(timePickerDialog);
                timePickerDialog.show();
            }
        });
        //waktu pengembalian
        jam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        if (rBtn1.isChecked()){
                            if (hour>21 || hour<7){
                                Toast.makeText(set_waktu_alamat.this,"Gaboleh",Toast.LENGTH_LONG).show();
                            } else {
                                jam2.setText(String.format(Locale.getDefault(),"%02d : %02d WIB",hour,minute));
                                waktuPengembalian = String.format(Locale.getDefault(),"%02d:%02d",hour,minute);
                            }
                        } else if (rBtn2.isChecked()){
                            if (hour>15 || hour<7){
                                Toast.makeText(set_waktu_alamat.this,"Gaboleh",Toast.LENGTH_LONG).show();
                            } else {
                                jam2.setText(String.format(Locale.getDefault(),"%02d : %02d WIB",hour,minute));
                                waktuPengembalian = String.format(Locale.getDefault(),"%02d:%02d",hour,minute);
                            }
                        } else {
                            Toast.makeText(set_waktu_alamat.this,"Mohon checklist dulu",Toast.LENGTH_LONG).show();
                        }
                    }
                };
                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(set_waktu_alamat.this,style,onTimeSetListener,hour,minute,true);
                timePickerDialog.show();
            }
        });
    }

    public void bawahDataToPesanan(){
        makePesanan = (Button) findViewById(R.id.buatPesanan);
        makePesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //membawah data dari pesanan dan berat
                String layanan;
                String waktu;
                String harga;
                String berat;


                layanan = getIntent().getStringExtra("layanan");
                waktu = getIntent().getStringExtra("waktu");
                harga = getIntent().getStringExtra("harga");
                berat = getIntent().getStringExtra("berat");

                System.out.println("jemput = "+hariJemput);
                System.out.println("kembali = "+hariKembali);
                System.out.println("waktu jemput = "+waktuJemput);
                System.out.println("Waktu Kembali = "+waktuPengembalian);

                Intent i = new Intent(getApplicationContext(),pesanan.class);

                //Data asli dari class setWaktualamat
                String alamat = "Bandung";
                i.putExtra("hariJemput",hariJemput);
                i.putExtra("hariKembali",hariKembali);
                i.putExtra("waktuJemput",waktuJemput);
                i.putExtra("waktuKembali",waktuPengembalian);
                i.putExtra("alamatUser",alamat);


                //data dari class sebelumnya
                i.putExtra("layanan",layanan);
                i.putExtra("waktu",waktu);
                i.putExtra("harga",harga);
                i.putExtra("berat",berat);
                startActivity(i);
            }
        });
    }
}