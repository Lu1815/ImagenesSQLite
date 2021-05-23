package com.example.imagenessqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;

    private ByteArrayOutputStream outputStream;
    private byte[] imageBytes;

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeImage(ModelClass obj){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStore = obj.getImage();

        outputStream = new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        imageBytes = outputStream.toByteArray();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_IMG_NAME, obj.getImgName());
        values.put(Utilidades.CAMPO_BITMAP, imageBytes);

        long cantidad = db.insert(Utilidades.TABLE_NAME, null, values);

        if( cantidad != 0 ){
            Toast.makeText(context, "Image added successfully", Toast.LENGTH_SHORT).show();
            db.close();
        } else {
            Toast.makeText(context, "ERROR: Image not added", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ModelClass> getAllImgs(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ModelClass> mc = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLE_NAME, null);

        while(cursor.moveToNext()){
            String imgName = cursor.getString(0);
            byte[] imgBytes = cursor.getBlob(1);

            Bitmap obj = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
            mc.add(new ModelClass(imgName, obj));
        }

        return mc;
//        if(cursor.getCount() != 0){
//
//            while(cursor.moveToNext()){
//                String imgName = cursor.getString(0);
//                byte[] imgBytes = cursor.getBlob(1);
//
//                Bitmap obj = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                mc.add(new ModelClass(imgName, obj));
//            }
//
//            return mc;
//        } else {
//            Toast.makeText(context, "There is no data in the database", Toast.LENGTH_SHORT).show();
//            return null;
//        }
    }
}
