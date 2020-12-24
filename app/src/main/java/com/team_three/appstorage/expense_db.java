package com.team_three.appstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class expense_db extends SQLiteOpenHelper {
    Context ctx;
    public expense_db(@Nullable Context context) {
        super(context, "expensedb", null, 1);
        this.ctx= context;
    }

    public void insertData(String desc, String amount, String extranote) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("description",desc);
        cv.put("money",amount);
        cv.put("note",extranote);
        db.insert("expenser",null,cv);
        db.close();
    }


    public void update(String desc, String amount) {
        ContentValues cv= new ContentValues();
        cv.put("description",desc);
        cv.put("money",amount);
        SQLiteDatabase db= this.getWritableDatabase();
        String values[]= {desc};
        db.update("expenser",cv,"description=?",values);
        db.close();
    }

    public void delete() {
        String query= "select * from expenser";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c1= db.rawQuery(query,null);
        if (c1.getCount() > 0) {
            db.delete("expenser", null, null);
        }
        else Toast.makeText(ctx, " sorry but no data to delete!", Toast.LENGTH_SHORT).show();
        db.close();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "create table expenser(description text, money text, note text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
