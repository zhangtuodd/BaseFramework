package com.example.zbj.baseframework;

import android.content.Context;
import android.util.Log;

import com.example.zbj.baseframework.app.RetrofitSingleton;
import com.example.zbj.baseframework.bean.ZhihuList;
import com.example.zbj.baseframework.model.Api;
import com.example.zbj.baseframework.model.ZhihuApi;
import com.example.zbj.baseframework.utils.DateUtils;
import com.example.zbj.baseframework.view.HomeFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zbj on 2017/3/16.
 */

public class HomePresenter implements HomeContract.Presenter {
    private Context context;
    private DateUtils dateUtils = new DateUtils();
    private HomeFragment homeFragment;
    private List<ZhihuList.Question> mList = new ArrayList<>();

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
        getData(true, Calendar.getInstance().getTimeInMillis());
    }

    @Override
    public void getData(final boolean flag, long time) {
        Log.i("tag","-time----"+dateUtils.homeDateFormat(time));
        if (flag) {
            homeFragment.showLoading();
        }
        final boolean b = flag;
        RetrofitSingleton.getInstance(context)
                .load(Api.ZHIHU_HISTORY)
                .create(ZhihuApi.class)
                .getZhihu(dateUtils.homeDateFormat(time))
                .enqueue(new Callback<ZhihuList>() {
                    @Override
                    public void onResponse(Call<ZhihuList> call, Response<ZhihuList> response) {
                        ZhihuList body = response.body();
                        List<ZhihuList.Question> stories = body.getStories();
                        if (flag){
                            mList.clear();
                        }
                        mList.addAll(stories);
                        homeFragment.showResult(mList);
                        homeFragment.stopLoading();
                    }

                    @Override
                    public void onFailure(Call<ZhihuList> call, Throwable t) {
                        homeFragment.DOWN_ERROR_FLAF = true;
                        homeFragment.stopLoading();
                        homeFragment.showError();

                    }
                });


    }

    @Override
    public void loadMore(long time) {
        getData(false,time);
    }
}
