package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengjw.customviewdemo.view.ThirdBezierCurveView;

public class ThirdBezierCurveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThirdBezierCurveView curveView = new ThirdBezierCurveView(this);
        setContentView(curveView);
    }
}
