package com.Desampara2.desamparados;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.StringBufferInputStream;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="desamparados"; //NOMBRE DE LA BD
    private  static final int db_scheme_version=1;
    private static  final  String nombre="nombre";
    private static  final  String Idaviso="email";
    private static  final  String email="email";
    //DEBE LLEVAR EL ID DE EL AVISO Y EL EMAIL DEL USUARIO
    private static String CREATE_TABLE ="create table favoritos ("+nombre+" Text)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, db_scheme_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREAR TABLA FAVORITOS
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertardatos(String nom){
        SQLiteDatabase db;
        db= this.getWritableDatabase();
        ContentValues registro= new ContentValues();
        registro.put(nombre, nom);
        db.insert("favoritos", null,registro);
        db.close();
    }

    public  void  eliminar (String nom){
        SQLiteDatabase db;
        db= this.getWritableDatabase();
        db.delete("favoritos", "nombre='"+nom+"'",null);
        db.close();
    }


}
