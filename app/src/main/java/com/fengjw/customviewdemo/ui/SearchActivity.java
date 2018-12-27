package com.fengjw.customviewdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fengjw.customviewdemo.view.SearchView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SearchView(this));
    }
}
