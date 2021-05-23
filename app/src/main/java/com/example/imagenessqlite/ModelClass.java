package com.example.imagenessqlite;

import android.graphics.Bitmap;

public class ModelClass {

    String imgName;
    Bitmap image;

    public ModelClass(String imgName, Bitmap image) {
        this.imgName = imgName;
        this.image = image;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
