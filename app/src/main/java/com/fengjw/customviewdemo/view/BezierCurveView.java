package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BezierCurveView extends View {

    private Paint mPaint = new Paint();

    private int centerX, centerY;
    private PointF point1, point2, control;

    private int mWidth;
    private int mHeight;

    public BezierCurveView(Context context) {
        super(context);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);

        point1 = new PointF(0, 0);
        point2 = new PointF(0, 0);
        control = new PointF(0, 0);
    }

    public BezierCurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;

        centerX = mWidth / 2;
        centerY = mHeight / 2;

        point1.x = centerX - 300;
        point1.y = centerY;

        point2.x = centerX + 300;
        point2.y = centerY;

        control.x = centerX;
        control.y = centerY - 500;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(mWidth / 2, mHeight / 2);
        mPaint.setColor(Color.BLACK);
        canvas.drawPoint(point1.x, point1.y, mPaint);
        canvas.drawPoint(point2.x, point2.y, mPaint);
        canvas.drawPoint(control.x, control.y, mPaint);

        canvas.drawLine(point1.x, point1.y, control.x, control.y, mPaint);
        canvas.drawLine(point2.x, point2.y, control.x, control.y, mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10f);
        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.quadTo(control.x, control.y, point2.x, point2.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        invalidate();
        return true;
    }
}
