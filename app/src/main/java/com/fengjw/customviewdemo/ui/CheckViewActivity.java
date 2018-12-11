package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fengjw.customviewdemo.R;
import com.fengjw.customviewdemo.view.CheckView;

public class CheckViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCheck;

    private CheckView mCheckview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkview);
//        mCheckview = new CheckView(this);

        initView();


    }

    private void initView() {
        mCheck = (Button) findViewById(R.id.check);
        mCheck.setOnClickListener(this);

        mCheckview = (CheckView) findViewById(R.id.checkview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check:
                // TODO 18/12/11
                mCheckview.start();
                break;
            default:
                break;
        }
    }
}
