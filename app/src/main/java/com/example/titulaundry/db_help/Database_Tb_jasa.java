package com.example.titulaundry.db_help;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database_Tb_jasa extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "laundry1.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Database_Tb_jasa(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //table jasa
        String sql1 = "CREATE TABLE jasa(id_layanan text PRIMARY KEY, jenis_jasa text null,deskripsi text null,durasi text null , harga text null);";
        sqLiteDatabase.execSQL(sql1);
        Log.d("Data","onCreate : "+sql1);
        sql1 = "INSERT INTO jasa(id_layanan,jenis_jasa,deskripsi,durasi,harga) VALUES('js123','Cuci Kering','Cuci hingga kering tanpa setrika','1 hari','5000');";
        sqLiteDatabase.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean inserDataLayanan(String id_layanan , String jenis_jasa,String deskripsi , String durasi , String harga){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_layanan",id_layanan);
        cv.put("jenis_jasa",jenis_jasa);
        cv.put("deskripsi",deskripsi);
        cv.put("durasi",durasi);
        cv.put("harga",harga);
        long result = db.insert("jasa",null,cv);
        if (result== -1){
            return false;
        } else {
            return true;
        }
    }
    public Cursor getDataLayanan(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM jasa",null);
        return cursor;
    }
}
