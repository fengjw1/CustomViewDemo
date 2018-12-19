package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengjw.customviewdemo.R;
import com.fengjw.customviewdemo.view.ArrowsCircleView;

public class ArrowCircleActivity extends AppCompatActivity {

    private ArrowsCircleView mArrawcircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_arrows);
        setContentView(new ArrowsCircleView(this));
//        initView();
    }

    private void initView() {
        mArrawcircle = (ArrowsCircleView) findViewById(R.id.arrawcircle);
    }
}
