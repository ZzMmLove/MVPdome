package cn.gst.mvp.mvpdemo.presenter;

import android.os.Handler;

import cn.gst.mvp.mvpdemo.bean.User;
import cn.gst.mvp.mvpdemo.biz.IUserBiz;
import cn.gst.mvp.mvpdemo.biz.OnLoginLisener;
import cn.gst.mvp.mvpdemo.biz.UserBiz;
import cn.gst.mvp.mvpdemo.ui.ILoginView;

/**
 * Created by Administrator on 4/1 0001.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;
    private ILoginView loginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(ILoginView loginView){
        this.loginView = loginView;
        this.userBiz = new UserBiz();
    }

    public void login(){
        loginView.showLodding();
        userBiz.login(loginView.getUsername(), loginView.getPassword(), new OnLoginLisener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.toMainActivity(user);
                        loginView.hideLodding();
                    }
                });

            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showFailedError();
                        loginView.hideLodding();
                    }
                });
            }
        });
    }

    public void clear(){
        loginView.clearUsername();
        loginView.clearPassword();
    }

}
