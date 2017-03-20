package com.example.zbj.baseframework;

import android.view.View;

/**
 * Created by zbj on 2017/3/16.
 */

public interface BaseView<T> {
    //初始化view
    void initView(View view);

    //set presenter
    void setPresenter(T presenter);

}
