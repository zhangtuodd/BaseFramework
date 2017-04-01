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

    /**
     * 创建类的单例
     * @param context
     * @return 本单例存在内存泄漏
     * 我们把可能导致 activity 泄漏的情况分为两类，一类是使用了进程全局（process-global）的静态变量，
     * 无论 APP 处于什么状态，都会一直存在，它们持有了对 activity 的强引用进而导致内存泄漏，
     * 另一类是生命周期长于 activity 的线程，它们忘记释放对 activity 的强引用进而导致内存泄漏
     *
     * 如果 onDestroy 执行完毕之后，activity 对象仍被 heap root 强引用，那垃圾回收器就无法将其回收
     */
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
