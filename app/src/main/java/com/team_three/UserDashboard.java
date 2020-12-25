package com.team_three;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.team_three.appstorage.expense_db;
import com.team_three.appstorage.profile_db;

public class UserDashboard extends AppCompatActivity implements View.OnClickListener, Dialog.Dialoglistener, ExpenseDialog.ExpenseDialoglistener {

    ImageView profile,expense,analysis;
    profile_db profiler_1;
    expense_db expense_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        profiler_1= new profile_db(UserDashboard.this);
        expense_1= new expense_db(UserDashboard.this);

        profile= (ImageView) findViewById(R.id.img_profile_id);
        expense= (ImageView) findViewById(R.id.img_expense_id);
        analysis= (ImageView) findViewById(R.id.img_analysis_id);


        profile.setOnClickListener(this);
        expense.setOnClickListener(this);
        analysis.setOnClickListener(this);

       /* SQLiteDatabase db= profiler_1.getReadableDatabase();
        SQLiteDatabase db1= expense_1.getReadableDatabase();

        String q1= "select * from profiler";
        String q2= "select * from expenser";

        Cursor data1= db.rawQuery(q1,null);
        Cursor data2= db1.rawQuery(q2,null);

        if (data1.getCount() > 0) {
            profiler_1.delete();
        }

        if (data2.getCount() > 0) {
            expense_1.delete();
        }   */

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_profile_id:
                openDialog();
                break;
            case R.id.img_expense_id:
                OPENDIALOG();
                break;
            case R.id.img_analysis_id:
                analysis();
                break;
            case R.id.txt_analysis_id:
                analysis();
                break;
            case R.id.txt_expense_id:
                OPENDIALOG();
                break;
        }
    }

    private void analysis() {
        SQLiteDatabase db1= profiler_1.getReadableDatabase();
        SQLiteDatabase db2= expense_1.getReadableDatabase();

        String s1= "select * from profiler";
        String s11= "select * from expenser";
        Cursor data1= db1.rawQuery(s1,null);
        Cursor data2= db2.rawQuery(s11,null);

        String amt="";
        long amt1=0;

        if (data1.getCount() > 0 && data1!=null) {
            if (data1.moveToFirst()) {
                do {
                    amt= data1.getString(1);
                } while (data1.moveToNext());
            }
        }

        if (data2.getCount() > 0 && data2!=null) {
            if (data2.moveToFirst()) {
                do {
                    amt1+= Long.parseLong(data2.getString(1));
                } while (data2.moveToNext());
            }
        }

        Intent intent= new Intent(UserDashboard.this, IncomeVsExpense.class);
        intent.putExtra("income", amt);
        intent.putExtra("expense", String.valueOf(amt1));
        startActivity(intent);
    }

    private void OPENDIALOG() {
        ExpenseDialog ed= new ExpenseDialog();
        ed.show(getSupportFragmentManager(), "custom expense dialog");
    }

    public void openDialog() {
        Dialog dialog= new Dialog(UserDashboard.this);
        dialog.show(getSupportFragmentManager(), "custom dialog");
    }

    @Override
    public void applyTexts(String name, String income) {
        if (name.length()> 0 && income.length() > 0) {
            profiler_1.insertData(name,income);
            Toast.makeText(this, "Data filled successfully!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Error in filling data! Please insert correct data!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void applyTexts_1(String description, String money, String note) {
        if (description.length()>0 && money.length()>0 && note.length()>0) {
            expense_1.insertData(description,money,note);
            Toast.makeText(this, "Data filled successfully!", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(UserDashboard.this, ExpenseDetails.class);
            intent.putExtra("desc",description);
            intent.putExtra("amt",money);
            intent.putExtra("extranote",note);
            startActivity(intent);
        }
        else Toast.makeText(this, "Error in filling data! Please insert correct data!", Toast.LENGTH_SHORT).show();
    }
}
