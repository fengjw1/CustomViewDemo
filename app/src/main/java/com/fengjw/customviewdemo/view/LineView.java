package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LineView  extends View {

    private Paint mPaint = new Paint();

    public LineView(Context context) {
        super(context);
        initPaint();
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initPaint(){
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(200,200, 500, 500, mPaint);
        canvas.drawLines(new float[]{
                200,200, 200, 500,
                300, 300, 300, 500,
                400,400, 400, 500,
                200,500, 500, 500
        }, mPaint);
    }
}
