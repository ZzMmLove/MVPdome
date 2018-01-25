package cn.gst.mvp.mvpdemo.biz;

import cn.gst.mvp.mvpdemo.bean.User;

/**
 * 登录状态监听接口
 * Created by Administrator on 4/1 0001.
 */

public interface OnLoginLisener {
    /**登录成功接口方法*/
    void loginSuccess(User user);
    /**登录失败接口方法*/
    void loginFailed();
}
