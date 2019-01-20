package com.dc.kouwei20190120.net;

import android.os.Handler;

import com.dc.kouwei20190120.cantract.Cantract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    public static OkHttpUtils mInstance;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();

    /**
     * ohttp框架
     * 拦截器
     */
    public OkHttpUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(httpLoggingInterceptor.getLevel().BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 单例
     * @return
     */
    public static OkHttpUtils getmInstance(){
        if (mInstance == null){
            synchronized (OkHttpUtils.class){
                if (mInstance == null){
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * post解析
     * @param url
     * @param params
     * @param okhttpCallBack
     */
    public void doPost(String url, HashMap<String,String> params, final Cantract.OkhttpCallBack okhttpCallBack){
        FormBody.Builder formbody = new FormBody.Builder();
        for (Map.Entry<String, String> mp : params.entrySet()) {
            formbody.add(mp.getKey(),mp.getValue());
        }
        Request request = new Request.Builder().url(url).post(formbody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       okhttpCallBack.failure("网络异常");
                   }
               });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       okhttpCallBack.success(string);
                    }
                });
            }
        });
    }
}
