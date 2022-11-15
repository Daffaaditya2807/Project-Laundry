package com.example.titulaundry.db_help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class Database_Tb_Pesanan extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pesan.db";
    private static final int DATABASE_VERSION = 1;
    private  SQLiteDatabase db;

    public Database_Tb_Pesanan(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE pesanan(id_pesanan text PRIMARY KEY, jasa text , berat_cucian text ,status text ,waktu text ,total_harga text );";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO pesanan(id_pesanan,jasa,berat_cucian,status,waktu,total_harga) VALUES('lyn123','Cuci Setrika','3','1 hari','Sedang Dalam Perjalanan','17.000')";
        sqLiteDatabase.execSQL(sql);


    }

    public boolean insertDataPesanan(String id_pesanan , String jasa , String berat_cucian,String status,String waktu , String total_harga){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_pesanan",id_pesanan);
        cv.put("jasa",jasa);
        cv.put("berat_cucian",berat_cucian);
        cv.put("status",status);
        cv.put("waktu",waktu);
        cv.put("total_harga",total_harga);
        long result = DB.insert("pesanan",null,cv);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataPesanan(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM pesanan",null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
