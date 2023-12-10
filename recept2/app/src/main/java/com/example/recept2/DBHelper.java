package com.example.recept2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Blob;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Recept.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Recept(ID INTEGER primary key autoincrement, Nev TEXT not null, Kep BLOB," +
                "Leiras TEXT, Hanyfo INT, Alapanyagok TEXT not null, Kategoria TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Recept");
    }

    public Boolean insertRecept(String Nev, String kep, String Leiras, int Hanyfo, String Alapanyagok, String Kategoria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nev", Nev);
        contentValues.put("Kep", kep);
        contentValues.put("Leiras", Leiras);
        contentValues.put("Hanyfo", Hanyfo);
        contentValues.put("Alapanyagok", Alapanyagok);
        contentValues.put("Kategoria", Kategoria);
        long result = db.insert("Recept", null, contentValues);
        if(result==-1){
            return false;
        }
        return true;
    }

    public Boolean updateRecept(int Id, String Nev, String Leiras, int Hanyfo, String Alapanyagok, String Kategoria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nev", Nev);
        contentValues.put("Kep", "");
        contentValues.put("Leiras", Leiras);
        contentValues.put("Hanyfo", Hanyfo);
        contentValues.put("Alapanyagok", Alapanyagok);
        contentValues.put("Kategoria", Kategoria);
        long result = db.update("Recept",  contentValues, "ID = ?", new String[]{String.valueOf(Id)});
        if(result==-1){
            return false;
        }
        return true;
    }

    public Boolean deleteRecept(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        long result = db.delete("Recept", "ID = ?", new String[]{String.valueOf(id)});
        if(result==-1){
            return false;
        }
        return true;
    }

    public ArrayList<Recept> getRecept()
    {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM Recept", null);

        // on below line we are creating a new array list.
        ArrayList<Recept> courseModalArrayList
                = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                courseModalArrayList.add(new Recept(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getInt(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    public Recept getReceptById(int id)
    {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM Recept where Recept.id="+"'"+id+"'", null);

        // on below line we are creating a new array list.
        /*Recept r = new Recept(
                cursorCourses.getInt(0),
                cursorCourses.getString(1),
                cursorCourses.getString(2),
                cursorCourses.getInt(3),
                cursorCourses.getString(4),
                cursorCourses.getString(5));*/

        Recept r = null;
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                r = new Recept(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getInt(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6));
                
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }

        // moving our cursor to first position.

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return r;
    }

    public ArrayList<Recept> getReceptbyKategoria(String kategoria)
    {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursorCourses
                = db.rawQuery("SELECT * FROM Recept where Recept.kategoria="+"'"+kategoria+"'", null);

        // on below line we are creating a new array list.
        ArrayList<Recept> courseModalArrayList
                = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                courseModalArrayList.add(new Recept(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getInt(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }
}
