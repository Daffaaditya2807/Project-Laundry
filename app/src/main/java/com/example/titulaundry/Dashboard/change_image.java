package com.example.titulaundry.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.titulaundry.API.ApiInterface;
import com.example.titulaundry.API.AppClient;
import com.example.titulaundry.Adapter.RealPathUtil;
import com.example.titulaundry.Model.ResponseImg;
import com.example.titulaundry.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class change_image extends AppCompatActivity {

    Button OpenGallery,savePic;
    String part_image;
    Cursor cursor;
    String path;
    ImageView circleImageView;
    ApiInterface apiInterface;
    final int  REQUEST_GALLERY = 9544;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);
        setOpenGallery();
        UploadImage();
    }
    public void UploadImage(){
        savePic = (Button) findViewById(R.id.savePic);
        savePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(path);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("imageupload", file.getName(), requestFile);
                RequestBody cus_name = RequestBody.create(MediaType.parse("multipart/form-data"),"1");

                apiInterface = AppClient.getClient().create(ApiInterface.class);
                Call<ResponseImg> imgCall = apiInterface.uploadImage(body,cus_name);
                imgCall.enqueue(new Callback<ResponseImg>() {
                    @Override
                    public void onResponse(Call<ResponseImg> call, Response<ResponseImg> response) {
                        if (response.body().getKode() == 1){
                            Toast.makeText(change_image.this, "Berhasil Upload", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseImg> call, Throwable t) {
                        Toast.makeText(change_image.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());
                    }
                });

            }
        });
    }
    public void setOpenGallery(){
        circleImageView = (ImageView) findViewById(R.id.imgProfile);
        OpenGallery = (Button) findViewById(R.id.ubah);
        OpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Open Gallery"),REQUEST_GALLERY);
                    Toast.makeText(change_image.this, "Open Galleerrtyy", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(change_image.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    Toast.makeText(change_image.this, "Open BO", Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 10);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Context context = change_image.this;
                path = RealPathUtil.getRealPath(context, uri);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                circleImageView.setImageBitmap(bitmap);

            } else {
                System.out.println("Mbuoh gagal");
            }
    }
}