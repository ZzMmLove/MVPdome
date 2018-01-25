package cn.gst.mvp.mvpdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.gst.mvp.mvpdemo.R;
import cn.gst.mvp.mvpdemo.bean.BottomItem;

/**
 *
 * Created by Administrator on 9/27 0027.
 */

public class AnimationBottom extends ViewGroup {

    private Context mContext;
    private AttributeSet mAttributeSet;
    private int backGroundColor;
    private float textSize;
    private int textColor;
    private int selectTextColor;
    private BottomAnimation mBottomAnimation = new BottomAnimation();
    private int itemMoveLeft = 0;//item左边的位置
    private int itemMoveRight = 0;//item移动右边的位置
    private int itemMoveCenter = 0;//item移动中心的位置

    private int itemMoveLastLeft = 0;//上次item移动左边的位置
    private int itemMoveLastRight = 0;//上次item移动右边的位置
    private int selectIndex = 0;//当前选中的位置
    private int selectLastIndex = 0;//上次选中的位置
    private int itemCount;//item的数量
    private int childCount;//子view的数量
    private int barHeight;//bar的高度
    private int barWidth;//bar的宽度
    private int itemWidth;//平均每个item的宽度
    private float touchDownX;//记录手指第一次按下的X位置
    private static final int CHILDCOUNTMAX = 5;//bottombar中能存在的item最大数;

    private ArrayList<BottomItem> mBottomItemArrayList = new ArrayList<>();
    private ArrayList<ImageView> mImageViews=new ArrayList<>();
    private ArrayList<TextView> mTextViews=new ArrayList<>();
    private int[] itemcolors = {0xFFF93008, 0xFF73ADCE, 0xFFEDC15E, 0xFFDC6394, 0xFF6BCEB0};//记录每个item的颜色
    private int[] itemCenterX = {0, 0, 0, 0, 0};//记录每个item的中心位置
    private float[] itemScale = {0.5f, 0.5f, 0.5f, 0.5f, 0.5f};//记录每个item的缩放比例

    private Paint mPaint = new Paint();
    private OnItemSelectListener mOnItemSelectListener;


    public AnimationBottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimationBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mAttributeSet = attrs;
        getAttr();
        setWillNotDraw(false);
        mBottomAnimation.setDuration(300);
        mBottomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearAnimation();
                itemMoveLastLeft = itemMoveLeft;
                itemMoveLastRight = itemMoveRight;
                selectLastIndex = selectIndex;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void build() throws Exception {
        itemCount = mBottomItemArrayList.size();
        if (itemCount > CHILDCOUNTMAX){
            throw new Exception("button item count is too large!");
        }
        itemWidth = getLayoutParams().width/ itemCount; //获得每个Button item的宽度
        //设置图片和描述文字
        for (BottomItem bottomItem : mBottomItemArrayList){
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(bottomItem.drawableRes);
            mImageViews.add(imageView);
            addView(imageView, itemWidth, 20);
        }

        for (BottomItem bottomItem : mBottomItemArrayList) {
            TextView textView = new TextView(mContext);
            textView.setTextSize(textSize);
            textView.setText(bottomItem.title);
            textView.setTextColor(textColor);
            textView.setGravity(Gravity.CENTER);
            mTextViews.add(textView);
            addView(textView, itemWidth, 16);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount = getChildCount();
         barWidth = getSize(300, widthMeasureSpec);
        barHeight = getSize(300, heightMeasureSpec);
        if (itemCount != 0)
        itemWidth =  barWidth / itemCount;
        for (int i = 0; i < childCount; i ++){
            View chilView = getChildAt(i);
            measureChild(chilView, widthMeasureSpec, heightMeasureSpec);
            chilView.getLayoutParams().width = itemWidth;
        }
        setSelectionIndex(0);
    }

    /**
     * 设置当前的位置
     * @param i
     */
    private void setSelectionIndex(int i) {
            selectIndex = i;
            itemMoveLeft = itemWidth * selectIndex;
            itemMoveRight = itemWidth * (selectIndex + 1);
            itemMoveLastRight = itemMoveRight;
            itemMoveLastLeft = itemMoveLeft;
            itemMoveCenter = itemMoveLeft + itemWidth / 2;
            selectLastIndex = i;
            postInvalidate();
    }

    /**
     * 设置属性
     */
    private void getAttr() {
        TypedArray typedArray = mContext.obtainStyledAttributes(mAttributeSet, R.styleable.AnimationBottomBar);
        backGroundColor = typedArray.getColor(R.styleable.AnimationBottomBar_backgruond, 0xFF5C4E71);
        textSize = typedArray.getDimension(R.styleable.AnimationBottomBar_textSize, 25.0f);
        textColor = typedArray.getColor(R.styleable.AnimationBottomBar_textColor, 0xffffff);
        selectTextColor = typedArray.getColor(R.styleable.AnimationBottomBar_selectTextColor, 0x639fff);
        typedArray.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        /*遍历每个item， 放置每个item的位置*/
        for (int i = 0; i < itemCount; i++) {               /*//遍历每一个item,放置item的位置*/
            itemCenterX[i] = (int) (itemWidth * (i + 0.5));/*//记录每个item的中心位置*/

            View childImageView = getChildAt(i);
            childImageView.layout(itemWidth * i, 0, itemWidth * (i + 1), 100);
            View childTextView = getChildAt(itemCount + i);
            childTextView.layout(itemWidth * i + childTextView.getWidth() / 4, 100, itemWidth * (i + 1), barHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < itemCount; i++){
            mPaint.setColor(itemcolors[i]);
            canvas.drawRect(itemWidth * i, 0, itemWidth * (i + 1), barHeight, mPaint);
            canvas.save();
        }
        mPaint.setColor(backGroundColor);
        //画两个长方形背景
        canvas.drawRect(0, 0, itemMoveLeft, barHeight, mPaint);
        canvas.drawRect(itemMoveRight, 0, itemWidth * 5, barHeight, mPaint);
        canvas.save();
        for (int i = 0; i < itemCount; i++) {
            int deltaX = Math.abs(itemMoveCenter - itemCenterX[i]);/*获得当前item移动中心点和item固定中心点的距离*/
            if (deltaX < itemWidth) {
                itemScale[i] = (float) (-0.5 * deltaX / itemWidth + 1);/*当距离小于一个item的宽度时调整item的缩放系数*/
            } else itemScale[i] = 0.5f;/*非选中的item的缩放系数固定为0.5*/
            /*对item的大小进行调整*/

            mImageViews.get(i).setScaleX(itemScale[i]);
            mImageViews.get(i).setScaleY(itemScale[i]);
//            View childTextView = getChildAt(itemCount + i);
            mTextViews.get(i).setScaleX(itemScale[i]);
            mTextViews.get(i).setScaleY(itemScale[i]);
        }
        super.onDraw(canvas);
    }

    private int getSize(int defaultSize, int measureSpec){
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }

    private class BottomAnimation extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            int position = selectIndex - selectLastIndex;
            /*判断不同方向的移动*/
            if (position < 0) {/*向左滑动*/
                itemMoveRight = (int) (itemMoveLastRight + interpolatedTime * itemWidth * position);
                itemMoveLeft = (int) (itemMoveLastLeft + setFirst(interpolatedTime) * itemWidth * position);
                itemMoveCenter = (int) (itemMoveLastRight + interpolatedTime * itemWidth * position) - itemWidth / 2;/*记录中心点移动的位置*/
            } else {/*向右滑动*/

                itemMoveRight = (int) (itemMoveLastRight + setFirst(interpolatedTime) * itemWidth * position);
                itemMoveLeft = (int) (itemMoveLastLeft + interpolatedTime * itemWidth * position);
                itemMoveCenter = (int) (itemMoveLastLeft + interpolatedTime * itemWidth * position) + itemWidth / 2;/*/记录中心点移动的位置*/

            }
            postInvalidate();
        }

        /*为了实现果冻效果,先移动的一侧要有快速效果*/
        private float setFirst(float interpolatedTime) {
            return (float) Math.sin(interpolatedTime * 0.5 * Math.PI);
        }
    }

    public AnimationBottom setItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.mOnItemSelectListener = onItemSelectListener;
        return this;
    }

    public interface OnItemSelectListener {
        void onItemSelectListener(int position);
    }

    private void changeTitleColor() {

        for (int i = 0; i < itemCount; i++) {
            if (i == selectIndex) {
                mTextViews.get(i).setTextColor(selectTextColor);
            } else {
                mTextViews.get(i).setTextColor(textColor);
            }
        }
    }

    /*发送点击监听*/
    private void sendListenerPosition(int position) {
        if (mOnItemSelectListener != null)
            mOnItemSelectListener.onItemSelectListener(position);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchDownX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (ev.getX() / itemWidth == touchDownX / itemWidth) {
                    selectIndex = (int) (ev.getX() / itemWidth);
                    changeTitleColor();

                    sendListenerPosition(selectIndex);
                    /*点击时开始动画*/
                    startAnimation(mBottomAnimation);
                }
                break;
        }
        return true;
    }

    /*添加item*/
    public AnimationBottom addItem(BottomItem bottomItem) {
        mBottomItemArrayList.add(bottomItem);
        return this;
    }


}
