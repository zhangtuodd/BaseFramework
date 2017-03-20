package com.example.zbj.baseframework;

/**
 * Created by zbj on 2017/3/16.
 */

public interface NewsContract {

    interface View extends BaseView {

        void showLoading();

        void stopLoading();

        void showError();

        void showResult();

    }

    interface Presenter extends BasePresenter {

        void loadData();

        void loadMore();

        void getData();

    }
}
