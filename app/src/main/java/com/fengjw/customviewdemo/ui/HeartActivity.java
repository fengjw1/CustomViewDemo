package com.fengjw.customviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fengjw.customviewdemo.R;
import com.fengjw.customviewdemo.view.HeartView;

public class HeartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnHeart;
    private HeartView mViewHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
//        setContentView(new HeartView(this));
        initView();
    }

    private void initView() {
        mBtnHeart = (Button) findViewById(R.id.heart_btn);
        mBtnHeart.setOnClickListener(this);
        mViewHeart = (HeartView) findViewById(R.id.heart_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.heart_btn:
                // TODO 18/12/13
                mViewHeart.reStart();
                break;
            default:
                break;
        }
    }
}
