package com.example.zbj.baseframework;

import com.example.zbj.baseframework.bean.ZhihuList;

import java.util.ArrayList;

/**
 * Created by zbj on 2017/3/16.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {

        void showLoading();

        void stopLoading();

        void showError();

        void showResult(ArrayList<ZhihuList.Question> list);
    }

    interface Presenter extends BasePresenter {
        void loadData();
        void getData(boolean flag);
        void loadMore();
    }
}
