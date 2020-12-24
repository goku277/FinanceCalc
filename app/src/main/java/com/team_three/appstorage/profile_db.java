package com.team_three.appstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class profile_db extends SQLiteOpenHelper {
    Context ctx;
    public profile_db(@Nullable Context context) {
        super(context, "profiledb", null, 1);
        this.ctx= context;
    }

    public void insertData(String name, String income) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("income",income);
        db.insert("profiler",null,cv);
        db.close();
    }


    public void update(String name, String income) {
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("income",income);
        SQLiteDatabase db= this.getWritableDatabase();
        String values[]= {name};
        db.update("profiler",cv,"name=?",values);
        db.close();
    }

    public void delete() {
        String query= "select * from profiler";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c1= db.rawQuery(query,null);
        if (c1.getCount() > 0) {
            db.delete("profiler", null, null);
        }
        else Toast.makeText(ctx, " sorry but no data to delete!", Toast.LENGTH_SHORT).show();
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "create table profiler(name text, income text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
