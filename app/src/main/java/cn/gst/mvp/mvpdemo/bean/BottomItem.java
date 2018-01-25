package cn.gst.mvp.mvpdemo.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by Administrator on 9/28 0028.
 */

import android.support.annotation.DrawableRes;

/**
 * Created by M on 2017/8/29.
 */

public class BottomItem {
    public int drawableRes;
    public String title;
    //    private ImageView mImageView;
    public BottomItem(@DrawableRes int drawableRes,String title){
        this.drawableRes=drawableRes;
        this.title=title;
    }
}
