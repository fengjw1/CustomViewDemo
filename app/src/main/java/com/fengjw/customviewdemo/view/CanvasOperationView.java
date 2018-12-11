package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasOperationView extends View {

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;

    public CanvasOperationView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasOperationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);
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

        //translate
//        canvas.translate(200, 200);
//        canvas.drawCircle(0, 0, 100, mPaint);
//
//        //scale
//        canvas.translate(-200, -200);
//        canvas.translate(mWidth / 4, mHeight / 4);
//        RectF rectF = new RectF(0, -400, 400, 0);
//        canvas.drawRect(rectF, mPaint);
//        canvas.scale(0.5f, 0.5f);
//        canvas.drawRect(rectF, mPaint);
//        canvas.scale(-0.5f, -0.5f, -50, 0);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.translate(mWidth/ 4, mHeight / 4);
//        canvas.scale(5f, 5f, 0, 0);
//
//        RectF rectF1 = new RectF(-300, -300, 300, 300);
//        for (int i = 0; i < 100; i ++){
//            canvas.drawRect(rectF1, mPaint);
//            canvas.scale(0.9f, 0.9f);
//        }

        //rotate
//        RectF rectF = new RectF(-400, -400, 400, 400);
//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.drawRect(rectF, mPaint);
//        canvas.rotate(40);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.drawCircle(0, 0, 300, mPaint);
//        canvas.drawCircle(0, 0, 250, mPaint);
//        for (int i = 0; i <360; i += 10){
//            canvas.drawLine(0, 300, 0, 250, mPaint);
//            canvas.rotate(10);//从圆心的方向上旋转10度，长度不变
//        }

        //4.skew 特殊类型的线性变换
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(-300, -300, 300, 300);
        canvas.drawRect(rectF, mPaint);
        canvas.skew(1, 0);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);

        canvas.save();

    }
}
