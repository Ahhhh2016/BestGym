package com.student.student_base_project.net;

import android.os.Build;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/***
 * 网路请求工具类
 */
public class HttpManager {
    private final Retrofit retrofit;
    public static ApiService apiService;
    private volatile static HttpManager INSTANCE;
    String baseUrl = NetUrl.COMURL;

    private HttpManager() {
        OkHttpClient defaultHttpClient = new OkHttpClient.Builder().addInterceptor(getInstanceInterceptor()).readTimeout(60, TimeUnit.SECONDS).writeTimeout(600, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS).build();
        ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
        retrofit = new Retrofit.Builder().addConverterFactory(ResponseConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(scalarsConverterFactory)
                .client(defaultHttpClient)
                .baseUrl(baseUrl)
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public Interceptor getInstanceInterceptor() {
        return chain -> {
            Request oldRequest = chain.request();
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host()/*).add.addQueryParameter("userId", SPUtil.getUserId()*/);

            Request.Builder url = oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body()).url(authorizedUrlBuilder.build());
            if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                url.addHeader("Connection", "close");
            }
            //String token = (String) SPUtil.get(ConstantUtils.appToken, "");
           // if (!TextUtils.isEmpty(token)) {
           //     url.addHeader("Authorization",  "Bearer " + token);
           // }

           // String customerToken = (String) SPUtil.get(ConstantUtils.customerToken, "");
          //  if (!TextUtils.isEmpty(customerToken)) {
          ////      url.addHeader("Current-Customer", customerToken);
            //}
            Request build = url.build();
            return chain.proceed(build);
        };
    }


    /**
     * 创建单例
     *
     * @return
     */
    public static ApiService getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                INSTANCE = new HttpManager();
            }
        }
        return INSTANCE.apiService;
    }
}
