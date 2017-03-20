package com.example.zbj.baseframework;

import android.content.Context;

import com.example.zbj.baseframework.app.RetrofitSingleton;
import com.example.zbj.baseframework.bean.ZhihuList;
import com.example.zbj.baseframework.model.Api;
import com.example.zbj.baseframework.model.ZhihuApi;
import com.example.zbj.baseframework.utils.DateUtils;
import com.example.zbj.baseframework.view.HomeFragment;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zbj on 2017/3/16.
 */

public class HomePresenter implements HomeContract.Presenter {
    private Context context;
    private DateUtils dateUtils = new DateUtils();
    private HomeFragment homeFragment;

    public HomePresenter(Context context, HomeFragment view) {
        view.setPresenter(this);
        this.context = context;
        this.homeFragment = view;
    }

    @Override
    public void autoRefresh() {
        loadData();
    }

    @Override
    public void loadData() {
        getData(true);
    }

    @Override
    public void getData(boolean flag) {
        if (flag) {
            homeFragment.showLoading();
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String s = dateUtils.homeDateFormat(timeInMillis);
        RetrofitSingleton.getInstance(context)
                .load(Api.ZHIHU_HISTORY)
                .create(ZhihuApi.class)
                .getZhihu(s)
                .enqueue(new Callback<ZhihuList>() {
                    @Override
                    public void onResponse(Call<ZhihuList> call, Response<ZhihuList> response) {
                        ZhihuList body = response.body();
                        ArrayList<ZhihuList.Question> stories = body.getStories();
                        homeFragment.showResult(stories);
                        homeFragment.stopLoading();
                    }

                    @Override
                    public void onFailure(Call<ZhihuList> call, Throwable t) {
                        homeFragment.stopLoading();
                        homeFragment.showError();
                    }
                });


    }

    @Override
    public void loadMore() {
        getData(false);
    }
}
