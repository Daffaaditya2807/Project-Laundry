package com.example.titulaundry.atur_pesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.titulaundry.R;
//import com.example.titulaundry.db_help.Database_Tb_user;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class set_waktu_alamat extends AppCompatActivity {
    RadioButton rBtn1 , rBtn2;
    ImageButton bckToPesanan;
    Button makePesanan,submit;
    Cursor cursor;
    DatePickerDialog picker;
//    public Database_Tb_user dbHelper;
    ConstraintLayout viewMenu;
    TextView tgl1 , tgl2,jam1 ,jam2,alamatDetailJemput,alamatDetailKirim,BtnAlamatJemput,BtnAlamatKirim,namaUser,namaKirim;
    AlertDialog dialog;
    int hour , minute;

    String waktuJemput , waktuPengembalian,hariJemput,hariKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_waktu_alamat);
        notif(set_waktu_alamat.this);
        PilihTanggal();
        inisiasiAlamat();
        setUser();
        setBckToPesanan();
        checkedButton();
        PickerTime();
        bawahDataToPesanan();
    }
    public void inisiasiAlamat(){
        alamatDetailJemput = (TextView) findViewById(R.id.alamatDetail);
        alamatDetailKirim = (TextView) findViewById(R.id.alamatDetailKirim);
    }

    public void setUser(){

//        namaUser = (TextView) findViewById(R.id.NamaUser);
//        namaKirim = (TextView) findViewById(R.id.NamaUserKirim);
//        dbHelper = new Database_Tb_user(this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT nama FROM user WHERE email = '"+getIntent().getStringExtra("email")+"'",null);
//        cursor.moveToFirst();
//        if (cursor.getCount()>0){
//            cursor.moveToPosition(0);
//            namaUser.setText(cursor.getString(0));
//            namaKirim.setText(cursor.getString(0));
//        }
    }

    public void IntentPesanan(){
        String layanan;
        String waktu;
        String harga;
        String berat;
        String gambar;

        layanan = getIntent().getStringExtra("layanan");
        waktu = getIntent().getStringExtra("waktu");
        harga = getIntent().getStringExtra("harga");
        berat = getIntent().getStringExtra("berat");
        gambar = getIntent().getStringExtra("imagee");

        Intent i = new Intent(getApplicationContext(),pesanan.class);
        if (rBtn1.isChecked()) {
            i.putExtra("hariJemput","Antar Sendiri");
            i.putExtra("layanan",layanan);
            i.putExtra("waktu",waktu);
            i.putExtra("harga",harga);
            i.putExtra("berat",berat);
            i.putExtra("email",getIntent().getStringExtra("email"));
            i.putExtra("imagee",gambar);
        }else {
            i.putExtra("hariJemput",hariJemput);
            i.putExtra("hariKembali",hariKembali);
            i.putExtra("waktuJemput",waktuJemput);
            i.putExtra("waktuKembali",waktuPengembalian);
            i.putExtra("alamatUserPick",alamatDetailJemput.getText().toString());
            i.putExtra("alamatUserSend",alamatDetailKirim.getText().toString());
            i.putExtra("layanan",layanan);
            i.putExtra("waktu",waktu);
            i.putExtra("harga",harga);
            i.putExtra("berat",berat);
        }

        startActivity(i);

        System.out.println("jemput = "+hariJemput);
        System.out.println("kembali = "+hariKembali);
        System.out.println("waktu jemput = "+waktuJemput);
        System.out.println("Waktu Kembali = "+waktuPengembalian);
        System.out.println("Alamat Pick = "+alamatDetailJemput.getText().toString());
        System.out.println("Alamat Send = "+alamatDetailKirim.getText().toString());

        //data dari class sebelumnya


    }
    private void showALertKirim() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_edit_alamat,null);
        EditText alamat;
        ImageView clsBtn;
        clsBtn = (ImageView) view.findViewById(R.id.closeButton);
        clsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        alamat = (EditText) view.findViewById(R.id.inputAlamat);
        submit = (Button) view.findViewById(R.id.submitAlamat);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamatDetailKirim.setText(alamat.getText().toString());
                dialog.dismiss();
            }
        });
        builder.setView(view);
        dialog = builder.create();
    }
    public void showALertJemput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_edit_alamat,null);
        EditText alamat;
        ImageView clsBtn;
        clsBtn = (ImageView) view.findViewById(R.id.closeButton);
        clsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        alamat = (EditText) view.findViewById(R.id.inputAlamat);
        submit = (Button) view.findViewById(R.id.submitAlamat);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamatDetailJemput.setText(alamat.getText().toString());
                dialog.dismiss();
            }
        });
        builder.setView(view);
        dialog = builder.create();

    }
    public void setBckToPesanan(){
        bckToPesanan = (ImageButton) findViewById(R.id.kembali);
        bckToPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentPesanan();
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-M-yyyy");

        tgl1.setText(simpleDateFormat.format(cldr.getTime()));
        hariJemput = simpleDateFormat2.format(cldr.getTime());

        tgl2.setText(simpleDateFormat.format(cldr.getTime()));
        hariKembali= simpleDateFormat2.format(cldr.getTime());
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
                    viewMenu.setVisibility(view.INVISIBLE);
//                    jam1.setText("07:00 WIB");
//                    jam2.setText("07:00 WIB");
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
                if (rBtn2.isChecked()){
                    //membawah data dari pesanan dan berat
                    if (waktuJemput == null ){
                        Toast.makeText(set_waktu_alamat.this,"Harap pilih jam Penjemputan",Toast.LENGTH_SHORT).show();
                    } else if (waktuPengembalian == null){
                        Toast.makeText(set_waktu_alamat.this,"Harap pilih jam pengiriman",Toast.LENGTH_SHORT).show();
                    }else {

                        Intent i = new Intent(getApplicationContext(),pesanan.class);
                        IntentPesanan();
                        finish();
                    }
                } else {
                        Intent i = new Intent(getApplicationContext(),pesanan.class);
                        IntentPesanan();
                        finish();
                }


            }
        });
    }

    public void funcEdit(View view) {
        BtnAlamatJemput = (TextView) findViewById(R.id.getAlamatJemput);
        BtnAlamatJemput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showALertJemput();
                dialog.show();
            }
        });
    }

    public void funcEditKirim(View view) {
        BtnAlamatKirim = (TextView) findViewById(R.id.getAlamatKirim);
        BtnAlamatKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showALertKirim();
                dialog.show();
            }
        });
    }


}