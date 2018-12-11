package com.fengjw.customviewdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fengjw.customviewdemo.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PieChartView pieChartView = new PieChartView(this);
        setContentView(pieChartView);
    }
}
