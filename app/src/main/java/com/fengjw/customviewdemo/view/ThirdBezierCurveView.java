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

public class ThirdBezierCurveView extends View {

    private Paint mPaint = new Paint();

    private int centerX, centerY;
    private PointF point1, point2;
    private PointF control1, control2;

    private boolean changeControlPoint = false;

    public ThirdBezierCurveView(Context context) {
        super(context);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        point1 = new PointF(0, 0);
        point2 = new PointF(0, 0);

        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
    }

    public ThirdBezierCurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        point1.x = centerX - 300;
        point1.y = centerY;

        point2.x = centerX + 300;
        point2.y = centerY;

        control1.x = centerX - 100;
        control1.y = centerY - 200;

        control2.x = centerX + 100;
        control2.y = centerY - 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画点
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10f);
        canvas.drawPoint(point1.x, point1.y, mPaint);
        canvas.drawPoint(point2.x, point2.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        //画辅助线
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(3f);
        canvas.drawLine(point1.x, point1.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y, point2.x, point2.y, mPaint);

        //三阶贝塞尔曲线
        mPaint.setStrokeWidth(8f);
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, point2.x, point2.y);
        canvas.drawPath(path, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {//这里这么写是为了解决move过程中不停改变状态导致两个控制点
            //显示为一个控制点的情况，仅供参考使用
            if (changeControlPoint) {
                changeControlPoint = false;
                control1.x = event.getX();
                control1.y = event.getY();
            } else {
                changeControlPoint = true;
                control2.x = event.getX();
                control2.y = event.getY();
            }
            invalidate();
        }
        return true;
    }
}
