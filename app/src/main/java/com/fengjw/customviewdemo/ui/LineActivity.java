package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengjw.customviewdemo.view.LineView;

public class LineActivity extends AppCompatActivity {

    private LineView mLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLineView = new LineView(this);
        setContentView(mLineView);
    }
}
