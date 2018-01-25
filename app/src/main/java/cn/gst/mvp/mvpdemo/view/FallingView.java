package cn.gst.mvp.mvpdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import cn.gst.mvp.mvpdemo.bean.FallObject;


/**
 * Created by Administrator on 12/22 0022.
 */

public class FallingView extends View{

    private Context mContext;
    private AttributeSet mAttrs;

    private int viewWidth;
    private int viewHeight;

    //设置默认宽高
    private static final int defaultWidth = 600;
    private static final int defaultHeight = 1200;

    private static final int intervalTime = 5;

    private Paint textPaint;
    private int snowY;

    private List<FallObject> fallObjects;


    public FallingView(Context context) {
        super(context);
    }

    public FallingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public FallingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        snowY = 0;
        fallObjects = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
        viewWidth = width;
        viewHeight = height;
    }

    private int measureSize(int defaultSize, int measureSpec){
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSeze = View.MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSeze;
        }else if (specMode == MeasureSpec.AT_MOST){
            result  = Math.min(result, specSeze);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(100, snowY, 25, textPaint);
//        getHandler().postDelayed(runable, intervalTime);
        if (fallObjects.size() > 0){
            for (int i = 0; i < fallObjects.size(); i++){
                fallObjects.get(i).drawObject(canvas);
            }
            getHandler().postDelayed(runable, intervalTime);
        }
    }

    private Runnable runable = new Runnable() {
        @Override
        public void run() {
           /* snowY += 15;
            //如果超出屏幕的高度测从重新绘制
            if (snowY > viewHeight){
                snowY = 0;
            }*/
            invalidate();
        }
    };

    private void addFallObject(final FallObject fallObject, final int num){
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                for (int i = 0;i < num; i++){
                    FallObject fallObject1 = new FallObject(fallObject.builder, viewWidth, viewHeight);
                    fallObjects.add(fallObject1);
                }
                invalidate();
                return true;
            }
        });
    }
}
