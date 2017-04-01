package com.example.zbj.baseframework.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zbj.baseframework.R;
import com.example.zbj.baseframework.adapter.ImAdapter;


/**
 * Created by zbj on 2017/3/9.
 */

public class IMFragment extends Fragment {
    private TabLayout tabLayout;
    private ImAdapter imAdapter;
    private String[] mTitles = {"联系人","交易提醒","系统消息"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_imfrg, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        /**
         * 只有指针，指针标题在Viewpager中设置
         * 定义TabLayout的样式
         * setupWithViewPager必须在ViewPager.setAdapter()之后调用
         * tabLayout.setTabMode:指针显示位置（靠左、平分居中）
         */
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        imAdapter = new ImAdapter(getChildFragmentManager(),mTitles);
        viewPager.addOnPageChangeListener(imAdapter.pageChangeListener());
        viewPager.setAdapter(imAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
