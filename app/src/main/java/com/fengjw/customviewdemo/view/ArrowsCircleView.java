package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.fengjw.customviewdemo.R;

public class ArrowsCircleView extends View {

    private Paint circlePaint = new Paint();

    private Bitmap mBitmap;

    private float mCenterX;
    private float mCenterY;

    private Matrix mMatrix;

    private float curValue;

    public ArrowsCircleView(Context context) {
        this(context, null);
    }

    public ArrowsCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        circlePaint.setStrokeWidth(5f);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.BLACK);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 10; //缩小十倍
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrows, options);

        mMatrix = new Matrix();

        curValue = 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);

        curValue += 0.005;
        if (curValue >= 1) {
            curValue = 0;
        }

        measure.getMatrix(measure.getLength() * curValue, mMatrix, PathMeasure.POSITION_MATRIX_FLAG
                | PathMeasure.TANGENT_MATRIX_FLAG);// 获取当前位置的坐标以及趋势的矩阵
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);
        //<-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)
        /**
         * 1.同样是坐标点移到屏幕中心. 2.通过getMatrix获取当前圆上点对应的matrix变换
         * 3.使用 preTranslate()去移动图片中心和圆上点重合
         * 这个方案是通过measure.getMatrix()方法来获取到的mMatrix,得到的这个mMatrix的含义就是
         * 圆上任意一点的切线作为x轴所对应的matrix变换。再做一个与x轴垂直的y轴就得到一个坐标系，
         * 想象一下这个坐标系(十字架)围绕圆转一周的情况。
         * 那么最原始的单位阵(坐标原点是屏幕的左上角(0,0))的位置，
         * 这个坐标系下去画这个icon刚好是顶在屏幕的左上角，
         * 那么想象一下getMatrix()对应相切的各个坐标系下去画这个icon的样子，
         * 这时使用preTranslate()的原因是因为此时每个坐标系下的icon去移动和
         * 圆上点重合就不再是-icon宽/2、-高/2了，而是这个距离经过当前matrix变换后的情况。
         * 举个栗子，假如圆最上面那个店作为起始点 (此时小飞机的方向是向右 ，对应的角度是 0度) ，
         * 在这时的相切坐标系刚好是圆心作为原点所对应的坐标系向上移动半径长度，这时将icon与起始点重合需要移动
         * -icon宽/2、-高/2。
         * 当顺时针转过45°之后，此时将icon与 45°这个点对应 所要移动的距离是
         * （0，根号二倍的 -icon高/2 ），也就是将icon向正上方移动 45° 与 0°
         * 要进行重合进行的操作数值也就变化了，这个变化就是隐含在了源矩阵M中 ，
         * (0° 进行重合移动距离所扫过的线是一个 向右下角倾斜45°的线，
         * 在圆经过45°之后 ，这个线也顺时针旋转了45° ， 就变成了一条向上的线 )
         *
         * 所以这里使用 preTranslate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2)方法 ，
         * 这个translate的效果就是 (-mBitmap.getWidth()/2,-mBitmap.getHeight()/2) 与之前矩阵mMatrix共同作用的结果。
         */

        canvas.drawPath(path, circlePaint);
        canvas.drawBitmap(mBitmap, mMatrix, circlePaint);

        invalidate();

    }
}
