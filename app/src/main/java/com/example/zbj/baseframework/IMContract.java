package com.example.zbj.baseframework;

/**
 * Created by zbj on 2017/3/16.
 */

public interface IMContract {
    interface View extends BaseView {

        void showLoading();

        void stopLoading();

        void showError();

        void showResult();
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void loadMore();
    }
}
