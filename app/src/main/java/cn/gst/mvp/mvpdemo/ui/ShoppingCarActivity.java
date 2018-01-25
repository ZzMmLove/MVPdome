package cn.gst.mvp.mvpdemo.ui;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import cn.gst.mvp.mvpdemo.R;
import cn.gst.mvp.mvpdemo.view.GoodsView;

public class ShoppingCarActivity extends AppCompatActivity {

    private ImageView iv_shop_add;
    private ImageView iv_shop_cart;
    private ViewGroup mViewGroup;

    private int mShoppingCarWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        getView();
        getData();

        ViewTreeObserver viewTreeObserver = iv_shop_cart.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                iv_shop_cart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mShoppingCarWidth = iv_shop_cart.getMeasuredWidth();
            }
        });
    }

    private void getData() {
        iv_shop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取商品坐标
                int[] goodsPoint = new int[2];
                iv_shop_add.getLocationInWindow(goodsPoint);
                //获取购物车坐标
                int[] shoppingCarPoint = new int[2];
                iv_shop_cart.getLocationInWindow(shoppingCarPoint);
                //生成商品的View
                GoodsView goodsView = new GoodsView(ShoppingCarActivity.this);
                goodsView.setCircleStartMovePoint(goodsPoint[0], goodsPoint[1]);
                goodsView.setCircleEndPoint(shoppingCarPoint[0], shoppingCarPoint[1]);
                mViewGroup.addView(goodsView);
                goodsView.startAnimation();
            }
        });

    }

    private void getView() {
        iv_shop_cart = (ImageView) findViewById(R.id.iv_shop_cart);
        iv_shop_add = (ImageView) findViewById(R.id.iv_shop_add);
        mViewGroup = (ViewGroup) getWindow().getDecorView();
    }
}
