package cn.gst.mvp.mvpdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by Administrator on 11/10 0010.
 */

public class GoodsView extends View {

    //小红点开始坐标
    Point mCircleStartPoint = new Point();
    //小红点结束坐标
    Point mCircleEndPoint = new Point();
    //小红点的控制坐标
    Point mCircleContrloPoint = new Point();
    //小红点的移动坐标
    Point mCircleMovePoint = new Point();
    //小红点的半径
    private int mRadius = 20;
    //小红点的笔画
    private Paint mCirclePaint;

    public GoodsView(Context context) {
        super(context);
        init(context);
    }

    public GoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GoodsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }

    /**
     * 画小红点
     * @param canvas
     */
    public void drawCircle(Canvas canvas){
        canvas.drawCircle(mCircleMovePoint.x, mCircleMovePoint.y, mRadius, mCirclePaint);
    }

    /**
     * 设置起始和移动点
     * @param x
     * @param y
     */
    public void setCircleStartMovePoint(int x, int y){
        this.mCircleStartPoint.x  = x;
        this.mCircleStartPoint.y = y;
        this.mCircleMovePoint.x = x;
        this.mCircleMovePoint.y = y;
    }

    /**
     * 设置结束点
     * @param x
     * @param y
     */
    public void setCircleEndPoint(int x, int y){
        this.mCircleEndPoint.x = x;
        this.mCircleEndPoint.y = y;
    }

    public void startAnimation(){
        if (mCircleStartPoint == null || mCircleEndPoint == null) return;;

        //设置控制点
        mCircleContrloPoint.x = ((mCircleStartPoint.x + mCircleEndPoint.x)/2);
        mCircleContrloPoint.y = 20;
        //设置值动画
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CirclePointEvaluator(), mCircleStartPoint, mCircleEndPoint);
        valueAnimator.setDuration(600);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point goodsViewPoint = (Point) animation.getAnimatedValue();
                mCircleMovePoint.x = goodsViewPoint.x;
                mCircleMovePoint.y = goodsViewPoint.y;
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(GoodsView.this);
            }
        });
        valueAnimator.start();
    }


    public class CirclePointEvaluator implements TypeEvaluator {

        /**
         * @param fraction 当前动画的进度
         * @param startValue 开始值
         * @param endValue 结束值
         * @return
         */
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;

            int x = (int) (Math.pow((1-fraction),2) * startPoint.x + 2 * (1-fraction) * fraction * mCircleContrloPoint.x + Math.pow(fraction,2) * endPoint.x);
            int y = (int) (Math.pow((1-fraction),2) * startPoint.y + 2 * (1-fraction) * fraction * mCircleContrloPoint.y + Math.pow(fraction,2) * endPoint.y);

            return new Point(x, y);
        }
    }

}
