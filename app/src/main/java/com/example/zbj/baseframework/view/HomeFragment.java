package com.example.zbj.baseframework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zbj.baseframework.HomeContract;
import com.example.zbj.baseframework.R;
import com.example.zbj.baseframework.adapter.HomeFrgAdapter;
import com.example.zbj.baseframework.bean.ZhihuList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by zbj on 2017/3/9.
 */

public class HomeFragment extends Fragment implements HomeContract.View {
    private HomeContract.Presenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<ZhihuList.Question> mList = new ArrayList<>();
    public HomeFrgAdapter adapter;
    private boolean ScrollDirection;
    private int mYear = Calendar.getInstance().get(Calendar.YEAR);
    private int mMonth = Calendar.getInstance().get(Calendar.MONTH);
    private int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    public static boolean DOWN_ERROR_FLAF = false;
    private boolean mIsRefreshing = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_homefrg, container, false);
        initView(view);
        //进入自动刷新
        presenter.autoRefresh();

        //手动下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("tag","onRefresh------");
                presenter.loadData();
            }
        });

        //解决recycleView下拉刷新clear数据同时进行上拉控件的bug
        //或者在数据加载出来之后在clear（本文方法）
//        recyclerView.setOnTouchListener(
//                new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (mIsRefreshing) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//        );

        /**
         * 踩坑：(无网络启动时，下拉刷新)
         * 在列表数据未加载出来时，上下滑动只会调用onScrollStateChanged
         * 所以无数据时（列表为空白）必须在onScrollStateChanged调用刷新
         *
         * DOWN_ERROR_FLAF:首次加载且无网络的情况
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("tag","onScrollStateChanged----------------");
                if (DOWN_ERROR_FLAF){
                    presenter.loadData();
                    DOWN_ERROR_FLAF = false;
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //最后一个item完全显示的位置
                    int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                    int itemCount = layoutManager.getItemCount();
                    Log.i("tag", "mYear--:" + mYear + ";mMonth--:" + mMonth + ";mDay--" + mDay + ";currentTime--:" + Calendar.getInstance().getTimeInMillis());
                    if (lastPosition == itemCount - 1 && ScrollDirection) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(mYear, mMonth, --mDay);
                        Log.i("tag", "transform-time--:" + calendar.getTimeInMillis());
                        presenter.loadMore(calendar.getTimeInMillis());
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ScrollDirection = dy > 0;
                Log.i("tag","onScrolled----------------");
            }
        });
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        //如果每个item的高度是固定的，可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.rl);
        refreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_red_dark));
    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
//                mIsRefreshing = true;
            }
        });
    }

    @Override
    public void stopLoading() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
//                mIsRefreshing = false;
            }
        },1000);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.load_failed, Toast.LENGTH_SHORT).show();
        Snackbar.make(refreshLayout, R.string.load_failed,Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadData();
                    }
                })
                .show();
    }

    @Override
    public void showResult(List<ZhihuList.Question> list) {
        if(adapter == null){
            Log.i("tag","adapter == null");
            adapter = new HomeFrgAdapter(getContext(), list);
            recyclerView.setAdapter(adapter);
        }else{
            Log.i("tag","adapter != null");
            adapter.notifyDataSetChanged();
        }

    }


}
