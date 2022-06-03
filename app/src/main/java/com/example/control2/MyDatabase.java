package com.example.control2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    public static String DB_NAME="societes.db";
    public static String T_NAME="Societe";
    public static String COL1="id";
    public static String COL2="raisonsociale";
    public static String COL3="Secteur_activite";
    public static String COL4="nb_employe";


    public MyDatabase(@Nullable Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String req="CREATE TABLE "+T_NAME+"("+COL1 +" integer primary key autoincrement,"+COL2 +" text,"+COL3+" text,"+COL4 +"int)";
        sqLiteDatabase.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public static long AddSociete(SQLiteDatabase sqLiteDatabase, Societe s){
        ContentValues ctv = new ContentValues();
        ctv.put(COL2,s.getNom());
        ctv.put(COL3,s.getSecteurActivité());
        ctv.put(COL4,s.getNombre());
        return sqLiteDatabase.insert(T_NAME,null,ctv);
    }

    public static long UpdateSociete(SQLiteDatabase db, Societe s){
        ContentValues ctv = new ContentValues();
        ctv.put(COL2,s.getNom());
        ctv.put(COL3,s.getSecteurActivité());
        ctv.put(COL4,s.getNombre());
        return db.update(T_NAME,ctv,"id="+s.getId(),null);
    }

    public static long DeleteSociete(SQLiteDatabase db, int id){
        return db.delete(T_NAME,"id="+id,null);
    }

    public static ArrayList<Societe> getAllSociete(SQLiteDatabase db){
        ArrayList<Societe> soc = new ArrayList<>();
        Cursor curs = db.rawQuery("SELECT * FROM " + T_NAME,null);
        while(curs.moveToNext()){
            Societe s= new Societe();
            s.setId(curs.getInt(0));
            s.setNom(curs.getString(1));
            s.setSecteurActivité(curs.getString(2));
            s.setNombre(curs.getInt(3));
            soc.add(s);
        }
        return soc;
    }

    public static Societe getOneSociete(SQLiteDatabase db, int id){
        Societe s = null;
        Cursor curs = db.rawQuery("SELECT * FROM " + T_NAME + " WHERE id = " + id,null);
        if(curs.moveToNext()){
            s = new Societe();
            s.setId(curs.getInt(0));
            s.setNom(curs.getString(1));
            s.setSecteurActivité(curs.getString(2));
            s.setNombre(curs.getInt(3));
        }
        return s;
    }
}
