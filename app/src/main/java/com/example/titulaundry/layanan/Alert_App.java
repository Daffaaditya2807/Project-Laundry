package com.example.titulaundry.layanan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.titulaundry.Login;
import com.example.titulaundry.R;
import com.example.titulaundry.SplashScreen;

public class Alert_App {

public static void alertBro(Context context,String pesan){
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    View layout_dialog = LayoutInflater.from(context).inflate(R.layout.alert,null);
    builder.setView(layout_dialog);

    ImageButton mengerti = layout_dialog.findViewById(R.id.mengerti);

    TextView alert = layout_dialog.findViewById(R.id.status);
    alert.setText(pesan);

    AlertDialog dialog = builder.create();
    dialog.show();
    dialog.setCancelable(false);
    dialog.getWindow().setGravity(Gravity.CENTER);

    mengerti.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    });
    new Handler().postDelayed(new Runnable() {

        @Override

        public void run() {

           dialog.dismiss();

        }

    }, 4*1000);

}

}
