package com.fengjw.customviewdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fengjw.customviewdemo.view.CanvasOperationView;

public class CanvasOperationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CanvasOperationView view = new CanvasOperationView(this);
        setContentView(view);
    }
}
