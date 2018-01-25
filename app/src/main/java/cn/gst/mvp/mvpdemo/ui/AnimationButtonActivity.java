package cn.gst.mvp.mvpdemo.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.gst.mvp.mvpdemo.R;
import cn.gst.mvp.mvpdemo.bean.BottomItem;
import cn.gst.mvp.mvpdemo.view.AnimationBottom;

/**
 *
 * Created by Administrator on 9/27 0027.
 */

public class AnimationButtonActivity extends AppCompatActivity {

    private AnimationBottom animationBottom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animaton);
        animationBottom = (AnimationBottom) findViewById(R.id.animation);
        try {
            animationBottom.addItem(new BottomItem(R.mipmap.ic_launcher, "AAA"))
                            .addItem(new BottomItem(R.mipmap.ic_launcher, "BBB"))
                            .addItem(new BottomItem(R.mipmap.ic_launcher, "CCC"))
                            .addItem(new BottomItem(R.mipmap.ic_launcher, "DDD"))
                            .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        animationBottom.setItemSelectListener(new AnimationBottom.OnItemSelectListener() {
            @Override
            public void onItemSelectListener(int position) {
                Toast.makeText(AnimationButtonActivity.this, "sb sb sb", Toast.LENGTH_LONG).show();
            }
        });
    }

}
