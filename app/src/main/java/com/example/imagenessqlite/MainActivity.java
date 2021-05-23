package com.example.imagenessqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etImgDetail;
    private ImageView imageViewObj;

    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imgToStore;

    private DatabaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etImgDetail = findViewById(R.id.etImgName);
        imageViewObj = findViewById(R.id.ivImg);

        dbh = new DatabaseHandler(getApplicationContext(), Utilidades.DB_NAME, null, 1);

        imageViewObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
            data.getData() != null){

            imageFilePath = data.getData();
            try {
                imgToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imageViewObj.setImageBitmap(imgToStore);
        }
    }

    public void storeImage(View v){

        String imgDet = etImgDetail.getText().toString();

        if( !imgDet.isEmpty() && imageViewObj.getDrawable() != null && imgToStore != null){
            dbh.storeImage(new ModelClass(imgDet, imgToStore));

            Toast.makeText(getApplicationContext(), "Image saved successfully", Toast.LENGTH_SHORT).show();

            //LIMPIAMOS CAMPOS
            etImgDetail.setText("");
            imageViewObj.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_camera_alt_24));
        } else {
            Toast.makeText(getApplicationContext(), "Please select image name and image", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToShowActivity(View v){
        startActivity(new Intent(MainActivity.this, ShowImagesActivity.class));
    }
}