package com.fengjw.customviewdemo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SearchView extends View {

    private Paint mPaint;

    //view宽高
    private int mViewWidth;
    private int mViewHeight;

    //状态类型
    private static enum State {
        NONE,
        STATING,
        SEARCHING,
        ENDING
    }

    //初始化状态
    private State mCurrentState = State.NONE;

    //搜索的路径、外部的圆
    private Path path_search;
    private Path path_circle;

    //Path的测量工具
    private PathMeasure mMeasure;

    //默认动画时间 2s
    private int defaultDuration = 2000;

    //控制整个过程的动画
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    //动画数值
    private float mAnimatorValue = 0;

    //动效过程监听器
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    //控制动画状态转换
    private Handler mAnimatorHandler;

    private boolean isOver = false;

    private int count = 0;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAll();
    }

    private void initAll() {
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimator();

        //进入开始动画
//        mCurrentState = State.STATING;
//        mStartingAnimator.start();
        mAnimatorHandler.sendEmptyMessage(0);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置画笔的样式，这里是圆形画笔
        mPaint.setAntiAlias(true);//抗锯齿
    }

    private void initPath() {
        path_circle = new Path();
        path_search = new Path();

        mMeasure = new PathMeasure();

        //放大镜圆环
        RectF oval1 = new RectF(-50, -50, 50, 50);
        path_search.addArc(oval1, 45, 359.9f);//顺时针画

        //外部圆环
        RectF oval2 = new RectF(-100, -100, 100, 100);
        path_circle.addArc(oval2, 45, -359.9f);

        float[] pos = new float[2];//位置

        mMeasure.setPath(path_circle, false);
        mMeasure.getPosTan(0, pos, null);//distance是距离起点的距离，这里是0.pos存的是该点的坐标值
        Log.d("fengjw", "pos : 0 = " + pos[0] + " 1 = " + pos[1]);
        path_search.lineTo(pos[0], pos[1]);//放大镜把手，从最后一个点连接到目标点，也就是从(0,0)连接到（70,70）

    }

    private void initListener() {
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {//动画结束更新下个动画
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    private void initHandler() {
        mAnimatorHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {//动画状态改变
                super.handleMessage(msg);
                switch (mCurrentState) {
                    case NONE:
                        count = 0;
                        mCurrentState = State.STATING;
                        mStartingAnimator.start();
                        break;
                    case STATING:
                        isOver = false;
                        mCurrentState = State.SEARCHING;
//                        mStartingAnimator.removeAllListeners();//清除上个动画的全部监听事件
                        mSearchingAnimator.start();
                        break;
                    case SEARCHING:
                        if (!isOver) {
                            mSearchingAnimator.start();
                            count++;
                            if (count > 2) {
                                isOver = true;
                            }
                        } else {
                            mCurrentState = State.ENDING;
                            mEndingAnimator.start();
                        }
                        break;
                    case ENDING:
                        mCurrentState = State.NONE;
                        break;
                }
            }
        };

    }

    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        canvas.drawColor(Color.parseColor("#0082D7"));
        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;
            case ENDING:
                mMeasure.setPath(path_search, false);
                Path dst3 = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue,
                        mMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
            case SEARCHING:
                mMeasure.setPath(path_circle, false);
                Path dst2 = new Path();
                float stop = mMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                mMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            case STATING:
                mMeasure.setPath(path_search, false);
                Path dst = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst, true);
                canvas.drawPath(dst, mPaint);
                break;
        }
    }

}
