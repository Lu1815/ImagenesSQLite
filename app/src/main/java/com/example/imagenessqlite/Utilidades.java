package com.example.imagenessqlite;

public class Utilidades {

    public static final String DB_NAME = "ImagesDB";
    public static final String TABLE_NAME = "Images";
    public static final String CAMPO_IMG_NAME = "name";
    public static final String CAMPO_BITMAP = "image";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            CAMPO_IMG_NAME + " TEXT, " + CAMPO_BITMAP + " BLOB)";

}
