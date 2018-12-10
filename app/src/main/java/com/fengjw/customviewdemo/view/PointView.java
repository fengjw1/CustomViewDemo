package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PointView extends View {

    private Paint mPaint = new Paint();

    public PointView(Context context) {
        super(context);
        initPaint();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(200,200, mPaint);
        canvas.drawPoints(new float[]{
              500, 500, 600, 600, 700, 700
        }, mPaint);
    }
}
