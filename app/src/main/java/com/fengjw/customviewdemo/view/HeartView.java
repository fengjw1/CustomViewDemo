package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class HeartView extends View {

    private static final float C = 0.551915024494f;//这个常量是用来计算圆形贝塞尔曲线控制点的

    private Paint mPaint = new Paint();
    private int centerX, centerY;

    private float mCircleRadius = 300;
    private float mDifference = mCircleRadius * C;

    private float[] mData = new float[8];
    private float[] mCtrl = new float[16];

    private float mDuration = 5000;
    private float mCount = 100;
    private float mCurrent = 0;
    private float mPiece = mDuration / mCount;

    private Paint assistPaint = new Paint();

    public HeartView(Context context) {
        super(context);
        Log.d("fengjw", "HeartView1");
    }

    public HeartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("fengjw", "HeartView2");
        mPaint.setStrokeWidth(3f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        assistPaint.setColor(Color.RED);
        assistPaint.setStrokeWidth(5);
        assistPaint.setStyle(Paint.Style.STROKE);

        initDataPoint();

        initCtrlPoint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        Log.d("fengjw", "x = " + centerX + " y = " + centerY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX, centerY);
        canvas.scale(1, -1);
        drawCoordinateSystem(canvas);

        drawAuxiliaryLine(canvas);

        //绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10f);
        Path path = new Path();
        path.moveTo(mData[0], mData[1]);
        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);//按照区间划分
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);
        canvas.drawPath(path, mPaint);

        //实现计算
        mCurrent += mPiece;
        if (mCurrent < mDuration) {
            mData[1] -= 120 / mCount;
            mCtrl[7] += 80 / mCount;
            mCtrl[9] += 80 / mCount;

            mCtrl[4] -= 20 / mCount;
            mCtrl[10] += 20 / mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }

    /**
     * 绘制坐标系
     *
     * @param canvas
     */
    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();

        canvas.drawLine(0, -centerY, 0, centerY, assistPaint);
        canvas.drawLine(-centerX, 0, centerX, 0, assistPaint);
        canvas.restore();
    }

    /**
     * 绘制辅助点
     *
     * @param canvas
     */
    private void drawAuxiliaryLine(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(3f);

        for (int i = 0; i < 8; i += 2) {
            canvas.drawPoint(mData[i], mData[i + 1], mPaint);
        }

        for (int i = 0; i < 16; i += 2) {
            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }

        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);
        }
        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);

    }

    /**
     * 初始化数据点
     */
    private void initDataPoint() {
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;
    }

    /**
     * 初始化控制点
     */
    private void initCtrlPoint() {
        mCtrl[0] = mData[0] + mDifference; //从y轴正方向开始
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];
    }

    public void reStart() {
        mCurrent = 0;
        initDataPoint();
        initCtrlPoint();
        invalidate();
    }

}
