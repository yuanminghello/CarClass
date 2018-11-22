package com.example.a123.dyt.util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpSingleton {
    private static HttpSingleton mHttpSingleton;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private HttpSingleton(){
        handler = new Handler(Looper.getMainLooper());
        okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }
    public static HttpSingleton getInstance(){
        if(mHttpSingleton==null){
            synchronized (HttpSingleton.class){
                if(mHttpSingleton==null){
                    mHttpSingleton=new HttpSingleton();
                }
            }
        }
        return mHttpSingleton;
    }

    public void doGet(String url, final UtilListener utilListener){
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(utilListener!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            utilListener.fail(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String json = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            utilListener.succeed(json);
                        }
                    });
                }
            }
        });


    }
    public void doPost(String url, Map<String,String> map, final UtilListener utilListener){
        FormBody.Builder builder = new FormBody.Builder();
        for(String key:map.keySet()){
            builder.add(key,map.get(key));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().post(formBody).url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(utilListener!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            utilListener.fail(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String json = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            utilListener.succeed(json);
                        }
                    });
                }
            }
        });

    }

    public interface UtilListener{
        void succeed(String json);
        void fail(Exception e);
    }

    private UtilListener mUtilListener;

    public void setmUtilListener(UtilListener mUtilListener) {
        this.mUtilListener = mUtilListener;
    }
}
