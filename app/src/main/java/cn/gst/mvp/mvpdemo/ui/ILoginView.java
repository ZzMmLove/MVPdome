package cn.gst.mvp.mvpdemo.ui;

import cn.gst.mvp.mvpdemo.bean.User;

/**
 * Created by Administrator on 4/1 0001.
 */

public interface ILoginView {

    String getUsername();

    String getPassword();

    void showLodding();

    void hideLodding();

    void toMainActivity(User user);

    void showFailedError();

    void clearUsername();

    void clearPassword();


}
