package cn.gst.mvp.mvpdemo.biz;

import cn.gst.mvp.mvpdemo.bean.HttpResult;
import cn.gst.mvp.mvpdemo.bean.User;
import cn.gst.mvp.mvpdemo.http.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * M 层 实现了五具体逻辑的接口，在这个类中做具体的逻辑判断，方法体的实现
 * 最终要通过Presenter层才能与View层取得关联
 * Created by Administrator on 4/1 0001.
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final OnLoginLisener onLoginLisener) {
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("zhongmo".equals(username) && "1314".equals(password)){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    onLoginLisener.loginSuccess(user);
                }else {
                    onLoginLisener.loginFailed();
                }
            }
        }.start();
    }

    @Override
    public Call<HttpResult> getResult(String userName, String userEmail, String userPass) {
        RetrofitUtils.getRetrofitApi().getReuslt(userName, userEmail, userPass).enqueue(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {

            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {

            }
        });
        return null;
    }
}
