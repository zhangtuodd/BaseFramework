package com.example.zbj.baseframework.app;

import android.content.Context;

import com.example.zbj.baseframework.model.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zbj on 2017/3/17.
 */

public class RetrofitSingleton {

    private static RetrofitSingleton retrofitSingleton;

    private RetrofitSingleton(Context context) {
    }
    //创建类的单例
    public static  RetrofitSingleton getInstance(Context context){
        if(retrofitSingleton == null){
            synchronized (RetrofitSingleton.class){
                if(retrofitSingleton == null){
                    retrofitSingleton = new RetrofitSingleton(context);
                }
            }

        }
        return retrofitSingleton;
    }

    public Retrofit load(String url){
        return new Retrofit.Builder()
                .baseUrl(Api.ZHIHU_HISTORY )
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
