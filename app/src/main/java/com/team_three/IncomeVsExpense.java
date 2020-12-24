package com.team_three;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class IncomeVsExpense extends AppCompatActivity {

    PieChart pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_vs_expense);

        pi= (PieChart) findViewById(R.id.donut_id);
        pi.setUsePercentValues(true);

        List<PieEntry> list1= new ArrayList<>();

        Intent intent= getIntent();
        String income= intent.getStringExtra("income").trim();
        String expense= intent.getStringExtra("expense").trim();

        String greater= Long.parseLong(income) > Long.parseLong(expense) ? "Income\n" +"\n"+ income : "Expense\n"+"\n"+expense;
        String smaller= Long.parseLong(income) < Long.parseLong(expense) ? "Income\n"+"\n"+income:"Expense\n"+"\n"+expense;

        list1.add(new PieEntry(40f,smaller));
        list1.add(new PieEntry(60f,greater));

        PieDataSet pieDataSet= new PieDataSet(list1, "IncomeVsExpense");
        PieData pieData= new PieData(pieDataSet);
        pi.setData(pieData);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
    }
}