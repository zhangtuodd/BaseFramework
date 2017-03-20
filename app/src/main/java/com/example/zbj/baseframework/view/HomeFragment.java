package com.example.zbj.baseframework.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zbj.baseframework.HomeContract;
import com.example.zbj.baseframework.MainActivity;
import com.example.zbj.baseframework.R;
import com.example.zbj.baseframework.adapter.ZhihuDialyAdapter;
import com.example.zbj.baseframework.bean.ZhihuList;

import java.util.ArrayList;


/**
 * Created by zbj on 2017/3/9.
 */

public class HomeFragment extends Fragment implements HomeContract.View {
    private HomeContract.Presenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ArrayList<ZhihuList.Question> list;
    private ZhihuDialyAdapter adapter;

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
                presenter.loadData();
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
            }
        });
    }

    @Override
    public void stopLoading() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(ArrayList<ZhihuList.Question> list) {
        this.list = list;
        adapter = new ZhihuDialyAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }


}
