package cn.gst.mvp.mvpdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ViewAnimator;

/**
 * 自定义波浪
 * Created by Administrator on 11/10 0010.
 */

public class BeaierWaveView extends View {

    //路径和笔画
    private Path mPath;
    private Paint mPaint;
    //偏移量
    private int mOffset;
    //记录屏幕的宽高
    private int mScreenHeight;
    private int mScreenWidth;


    public BeaierWaveView(Context context) {
        super(context);
        init(context);
    }

    public BeaierWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BeaierWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //获取屏幕的高度
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        //路径画笔设置
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(8);
        setViewanimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //重置路径（清除）
        mPath.reset();
        //贝塞尔曲线
        mPath.moveTo(-mScreenWidth + mOffset, mScreenHeight / 2);
//        mPath.quadTo(-mScreenWidth*3 /4 + mOffset, mScreenHeight / 2 - 100, -mScreenWidth / 2 + mOffset, mScreenHeight / 2);
//        mPath.quadTo(-mScreenWidth / 4 + mOffset, mScreenHeight / 2 + 100, 0 + mOffset, mScreenHeight / 2);
//        mPath.quadTo(mScreenWidth /4 + mOffset, mScreenHeight / 2 - 100, -mScreenWidth / 2 + mOffset, mScreenHeight / 2);
//        mPath.quadTo(mScreenWidth*3 /4 + mOffset, mScreenHeight / 2 + 100, mScreenWidth + mOffset, mScreenHeight / 2);

        //简化写法
        for (int i = 0; i < 2; i++) {
            mPath.quadTo(-mScreenWidth * 3 / 4 + (mScreenWidth * i) + mOffset, mScreenHeight / 2 - 100, -mScreenWidth / 2 + (mScreenWidth * i) + mOffset, mScreenHeight / 2);
            mPath.quadTo(-mScreenWidth / 4 + (mScreenWidth * i) + mOffset, mScreenHeight / 2 + 100, +(mScreenWidth * i) + mOffset, mScreenHeight / 2);
        }

        //空白部分填充
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 设置动画效果
     */
    private void setViewanimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mScreenHeight);
        valueAnimator.setDuration(1200);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
