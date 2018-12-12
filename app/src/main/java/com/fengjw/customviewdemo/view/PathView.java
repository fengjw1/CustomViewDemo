package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private Paint mPaint = new Paint();

    private int mWidth, mHeight;

    public PathView(Context context) {
        super(context);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
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
        canvas.translate(mWidth / 2, mHeight / 2);

        //填充模式
        //        Path path = new Path();
//        path.setFillType(Path.FillType.WINDING);
//        RectF rectF = new RectF(-200, -200, 200, 200);
//        RectF rectF1 = new RectF(-400, -400, 400, 400);
//        path.addRect(rectF, Path.Direction.CCW);
//        path.addRect(rectF1, Path.Direction.CW);
//        canvas.drawPath(path, mPaint);

        //boolean运算
        RectF rectF = new RectF(-300, -300, 300, 300);
        mPaint.setColor(0xFFFE5200);
        canvas.drawRect(rectF, mPaint);
        mPaint.setColor(Color.BLACK);
        Path path = new Path();
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        path1.addRect(0, -200, 200, 200, Path.Direction.CW);
        path2.addCircle(0, -100, 100, Path.Direction.CW);
        path3.addCircle(0, 100, 100, Path.Direction.CCW);

        path.op(path1, Path.Op.DIFFERENCE);//画出左半圆
        path.op(path2, Path.Op.UNION);//加上上小半圆
        path.op(path3, Path.Op.DIFFERENCE);//减去下小半圆
        canvas.drawPath(path, mPaint);


    }
}
