package cn.gst.mvp.mvpdemo.biz;

import cn.gst.mvp.mvpdemo.bean.HttpResult;
import retrofit2.Call;

/**
 * 业务逻辑接口，没有具体的逻辑
 * Created by Administrator on 3/21 0021.
 */

public interface IUserBiz {
    /**
     * M 层的登录逻辑接口
     * @param username  用户名
     * @param password 用户密码
     * @param onLoginLisener
     */
    void login(String username, String password, OnLoginLisener onLoginLisener);
    Call<HttpResult> getResult(String userName, String userEmail, String userPass);



}
