package com.example.zbj.baseframework.model;

import com.example.zbj.baseframework.bean.ZhihuList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zbj on 2017/3/16.
 */

public interface ZhihuApi {

    @GET("{date}")
    Call<ZhihuList> getZhihu(@Path("date") String time);
}
