package cn.gst.mvp.mvpdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import cn.gst.mvp.mvpdemo.R;

/**
 * 自定义礼物赠送动画
 * Created by Administrator on 11/11 0011.
 */

public class StartViewGroup extends RelativeLayout implements View.OnClickListener {

    private Bitmap mBitmap;

    private Path mPath;
    private Paint mPaint;

    private int mScreenWidth;
    private int mScreenHeight;

    //记录数据点和控制点（由于是三阶贝塞尔曲线，所以有两个控制点）
    private Point mStartPoint;
    private Point mEndPoint;
    private Point mControlOnePoint;
    private Point mControlTwoPoint;

    protected Random mRandom;
    private int[] mColor = {Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.MAGENTA, Color.YELLOW};

    public StartViewGroup(Context context) {
        super(context);
        initView();
    }

    public StartViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StartViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化共用的属性
     */
    private void initView() {
        //设置画笔，路径
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
        mRandom = new Random();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 画星星并随机设置颜色
     * @param color
     * @return
     */
    private Bitmap drawaStart(int color){
        Bitmap ourBimap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ourBimap);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawColor(color, PorterDuff.Mode.SRC_IN);
        canvas.setBitmap(null);
        return ourBimap;
    }

    protected void addStart(){
        Bitmap bitmap = drawaStart(mRandom.nextInt(mColor.length));
        final ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(120, 100);
        layoutParams.addRule(CENTER_HORIZONTAL);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        imageView.setImageBitmap(bitmap);
        addView(imageView, layoutParams);

        Point controlOnePoint = this.mControlOnePoint;
        Point controlTwoPoint = this.mControlTwoPoint;
        Point controlStartPoint = this.mStartPoint;
        Point controlEndPoint = this.mEndPoint;

        //设置属性动画
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new StartTypeEvaluator(controlOnePoint, controlTwoPoint), controlStartPoint, controlEndPoint);
        valueAnimator.setDuration(2500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                StartViewGroup.this.removeView(imageView);
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(4000);
        animatorSet.play(valueAnimator).with(objectAnimator);
        animatorSet.start();
        valueAnimator.start();
    }

    /**
     * 获取屏幕的高度和宽度并设置数据点和控制点
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mScreenWidth = w;
        this.mScreenHeight = h;
        mStartPoint = new Point(mScreenWidth / 2, mScreenHeight);
        mStartPoint = new Point(mScreenWidth / 2, 0);
        mControlOnePoint = new Point(mScreenWidth, mScreenHeight * 3 / 4);
        mControlTwoPoint = new Point(0, mScreenHeight / 4);
        setBackgroundColor(Color.WHITE);
    }


    public class StartTypeEvaluator implements TypeEvaluator<Point>{

        //记录控制点
        private Point controlOnePoint, controlTwoPoint;

        public StartTypeEvaluator(Point controlOnePoint, Point controlTwoPoint){
            this.controlOnePoint = controlOnePoint;
            this.controlTwoPoint = controlTwoPoint;
        }

        /**
         * 利用三阶贝塞尔曲线来算出中间点的坐标
         * @param fraction
         * @param startValue
         * @param endValue
         * @return
         */
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int x = (int) (startValue.x * Math.pow((1 - fraction), 3) + 3 * controlOnePoint.x * fraction * Math.pow((1 - fraction), 2) + 3 *
                    controlTwoPoint.x * Math.pow(fraction, 2) * (1 - fraction) + endValue.x * Math.pow(fraction, 3));
            int y = (int) (startValue.y * Math.pow((1 - fraction), 3) + 3 * controlOnePoint.y * fraction * Math.pow((1 - fraction), 2) + 3 *
                    controlTwoPoint.y * Math.pow(fraction, 2) * (1 - fraction) + endValue.y * Math.pow(fraction, 3));
            return new Point(x, y);
        }
    }

    @Override
    public void onClick(View v) {
        //addStart();
    }

    /**
     * 动态生成对应的坐标
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mStartPoint = new Point(mScreenWidth / 2, mScreenHeight);
        mEndPoint = new Point((int) (mScreenWidth / 2 + 150 * mRandom.nextFloat()), 0);
        mControlOnePoint = new Point((int) (mScreenWidth * mRandom.nextFloat()), (int) (mScreenHeight *3 * mRandom.nextFloat() / 4));
        mControlTwoPoint = new Point(0, (int) (mScreenHeight * mRandom.nextFloat() / 4));
        addStart();
        return true;
    }
}
