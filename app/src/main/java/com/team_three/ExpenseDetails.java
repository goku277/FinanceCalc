package com.team_three;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.team_three.appstorage.expense_db;
import com.team_three.appstorage.profile_db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ExpenseDetails extends AppCompatActivity {

    ArrayList<String> desc= new ArrayList<>(), money= new ArrayList<>(), note= new ArrayList<>();
    profile_db profileDb;
    expense_db expenseDb;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        profileDb= new profile_db(ExpenseDetails.this);
        expenseDb= new expense_db(ExpenseDetails.this);
        total= (TextView) findViewById(R.id.total_id);

        initDetialsFetch();
    }

    private void initDetialsFetch() {
        Intent intent= getIntent();
        String desc_1= intent.getStringExtra("desc").trim();
        String amt_1= intent.getStringExtra("amt").trim();
        String extranote_1= intent.getStringExtra("extranote").trim();

       // expenseDb.insertData(desc_1,amt_1,extranote_1);

        SQLiteDatabase db= expenseDb.getReadableDatabase();
        String q1= "select * from expenser";
        Cursor data= db.rawQuery(q1,null);

       /* if (data.getCount() > 0) {
            expenseDb.delete();
        }  */
        long sum=0;
        if (data.getCount() > 0 && data!=null) {
            if (data.moveToFirst()) {
                do {
                    desc.add(data.getString(0));
                    money.add(data.getString(1));
                    note.add(data.getString(2));
                    System.out.println(data.getString(0) + "\t" + data.getString(1) + "\t" + data.getString(2));
                    sum+= Long.parseLong(data.getString(1));
                } while (data.moveToNext());
            }
        }

        SQLiteDatabase db1= profileDb.getReadableDatabase();
        String q11= "select * from profiler";
        Cursor data1= db1.rawQuery(q11,null);
        String incomeRecord="";
        if (data1.getCount() > 0 && data1!=null) {
            if (data1.moveToFirst()) {
                do {
                    incomeRecord= data1.getString(1);
                } while (data1.moveToNext());
            }
        }
        if (Long.parseLong(incomeRecord) > sum) {
            total.setText("Total : " + sum);
            System.out.println(sum);
            System.out.println(incomeRecord);
        }
        else {
            total.setText("oops! total exceeded income money!");
            System.out.println(sum);
            System.out.println(incomeRecord);
        }
        initRecyclerView();
    }
    public void initRecyclerView() {
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView_id);
        ExpenseAdapter expenseAdapter= new ExpenseAdapter(ExpenseDetails.this, desc, money, note);
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}