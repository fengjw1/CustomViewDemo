package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengjw.customviewdemo.view.PointView;

public class PointActivity extends AppCompatActivity {

    private PointView mPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPointView = new PointView(this);
        setContentView(mPointView);
    }
}
