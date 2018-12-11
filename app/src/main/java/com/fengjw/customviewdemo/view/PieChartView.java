package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fengjw.customviewdemo.data.PieData;

import java.util.ArrayList;

public class PieChartView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private float mStartAngle = 0;
    private ArrayList<PieData> mData;
    private int mWidth, mHeight;

    private Paint mPaint = new Paint();

    public PieChartView(Context context) {
        super(context);
        initPaint();
        initData();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mData)
            return;
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            mPaint.setColor(mColors[i]);
            Log.d("fengjw", "angle : " + pieData.getAngle());
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();
        }
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
    }

    private void initData() {
        mData = new ArrayList<>();
        PieData pieData = new PieData("fengjw", 30);
        PieData pieData1 = new PieData("fengjw", 60);
        PieData pieData2 = new PieData("fengjw", 20);
        PieData pieData3 = new PieData("fengjw", 90);
        PieData pieData4 = new PieData("fengjw", 90);
        PieData pieData5 = new PieData("fengjw", 70);
        PieData pieData6 = new PieData("fengjw", 40);
        mData.add(pieData);
        mData.add(pieData1);
        mData.add(pieData2);
        mData.add(pieData3);
        mData.add(pieData4);
        mData.add(pieData5);
        mData.add(pieData6);

    }

}
