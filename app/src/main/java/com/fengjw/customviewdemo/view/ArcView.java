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

public class ArcView extends View {

    private Paint mPaint = new Paint();

    public ArcView(Context context) {
        super(context);
        initPaint();
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initPaint(){
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(200, 200, 600, 600);

        canvas.drawArc(rectF, 0, 120, true, mPaint);

        RectF rectF1 = new RectF(200, 700, 600, 1100);
        canvas.drawArc(rectF1, 0, 120, false, mPaint);

        RectF rectF2 = new RectF(200, 1200, 600, 1600);
        canvas.drawArc(rectF2, 0, 360, false, mPaint);
    }
}
