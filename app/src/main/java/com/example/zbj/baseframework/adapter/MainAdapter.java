package com.example.zbj.baseframework.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.zbj.baseframework.HomePresenter;
import com.example.zbj.baseframework.IMPresenter;
import com.example.zbj.baseframework.MainActivity;
import com.example.zbj.baseframework.NewsPresenter;
import com.example.zbj.baseframework.view.BottomLayout;
import com.example.zbj.baseframework.view.HomeFragment;
import com.example.zbj.baseframework.view.IMFragment;
import com.example.zbj.baseframework.view.NewsFragment;
import com.example.zbj.baseframework.view.PersonFragment;

/**
 * Created by zbj on 2017/3/9.
 */

public class MainAdapter extends FragmentPagerAdapter {
    Fragment homeFragment = null;
    Fragment imFragment = null;
    Fragment newsFragment = null;
    Fragment personFragment = null;
    BottomLayout bottomLayout = null;
    Context context;
    HomePresenter homePresenter;
    IMPresenter imPresenter;
    NewsPresenter newsPresenter;
    ViewPager vp = null;

    public MainAdapter(FragmentManager fm, BottomLayout tab, MainActivity mainActivity) {
        super(fm);
        this.bottomLayout = tab;
        this.context = mainActivity;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                fragment = homeFragment;
                homePresenter = new HomePresenter(context,(HomeFragment)fragment);
                break;
            case 1:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                }
                fragment = newsFragment;
                newsPresenter = new NewsPresenter(context,(NewsFragment)fragment);
                break;
            case 2:
                if (personFragment == null) {
                    personFragment = new PersonFragment();
                }
                fragment = personFragment;
                break;
            case 3:
                if (imFragment == null) {
                    imFragment = new IMFragment();
                }
                fragment = imFragment;
                imPresenter = new IMPresenter(context,(IMFragment)fragment);
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }


    public ViewPager.OnPageChangeListener set() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomLayout.setPicture(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
