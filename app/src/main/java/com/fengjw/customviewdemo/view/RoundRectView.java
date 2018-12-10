package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RoundRectView extends View {

    private Paint mPaint = new Paint();

    public RoundRectView(Context context) {
        super(context);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(200,200, 500, 500, 30, 30, mPaint);

        RectF rectF = new RectF(300, 600, 600, 1200);
        canvas.drawRoundRect(rectF, 150, 300, mPaint);//    x/2; y/2
    }
}
