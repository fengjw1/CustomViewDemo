package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class OvalView extends View {

    private Paint mPaint = new Paint();

    public OvalView(Context context) {
        super(context);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(200, 200, 800, 600);
        canvas.drawOval(rectF, mPaint);

        RectF rectF1 = new RectF(200, 800, 600, 1200);//x=y
        canvas.drawOval(rectF1, mPaint);
    }
}
