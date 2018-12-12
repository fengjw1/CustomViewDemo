package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengjw.customviewdemo.view.BezierCurveView;

public class BezierCurveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BezierCurveView bezierCurveView = new BezierCurveView(this);
        setContentView(bezierCurveView);
    }
}
