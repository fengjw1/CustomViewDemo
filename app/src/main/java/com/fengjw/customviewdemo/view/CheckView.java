package com.fengjw.customviewdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fengjw.customviewdemo.R;


public class CheckView extends View {

    private Paint mPaint = new Paint();

    private Context mContext;
    private int currentPage = -1;
    private int maxPage = 12;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("fengjw", "handleMessage");
            if (currentPage < maxPage && currentPage >= 0) {
                Log.d("fengjw", "1");
                invalidate();
                currentPage++;
                this.sendEmptyMessageDelayed(0, 100);
            } else {
                Log.d("fengjw", "2");
//                currentPage = -1;
                invalidate();
            }
        }
    };
    private Bitmap mBitmap;
    private int mWidth, mHeight;

    public CheckView(Context context) {
        super(context);

    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mContext = context;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.checkmark);
        Log.d("fengjw", "CheckView");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        Log.d("fengjw", "onDraw");
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 240, mPaint);
        int height = mBitmap.getHeight();
        Log.d("fengjw", "height : " + height);
        Rect rectF = new Rect(height * currentPage, 0, height * (currentPage + 1), height);
        RectF rectF1 = new RectF(-200, -200, 200, 200);
        canvas.drawBitmap(mBitmap, rectF, rectF1, null);
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffFF5317);
        mPaint.setStrokeWidth(5f);
    }

    public void start() {
        Log.d("fengjw", "start");
        currentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, 100);
    }
}
