package com.fengjw.customviewdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fengjw.customviewdemo.view.OvalView;

public class OvalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new OvalView(this));
    }
}
