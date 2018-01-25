package cn.gst.mvp.mvpdemo.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 6/30 0030.
 */

public class RetrofitUtils {
    private static RetrofitApi mRetrofitApi;


    public static RetrofitApi getRetrofitApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.shiyan360.cn/index.php/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitApi = retrofit.create(RetrofitApi.class);
        return mRetrofitApi;
    }


}
