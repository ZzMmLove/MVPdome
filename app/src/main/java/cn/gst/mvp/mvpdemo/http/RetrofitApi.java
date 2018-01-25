package cn.gst.mvp.mvpdemo.http;

import cn.gst.mvp.mvpdemo.bean.HttpResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 6/30 0030.
 */

public interface RetrofitApi {

    @GET("user_signup")
    Call<HttpResult> getReuslt(@Query("user_name") String userName,
                               @Query("user_email") String userEmail,
                               @Query("user_pass") String userPass);
}
