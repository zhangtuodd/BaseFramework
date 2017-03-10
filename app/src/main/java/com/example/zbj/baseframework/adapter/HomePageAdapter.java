package com.example.zbj.baseframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.zbj.baseframework.view.BottomLayout;
import com.example.zbj.baseframework.view.HomeFragment;
import com.example.zbj.baseframework.view.IMFragment;
import com.example.zbj.baseframework.view.NewsFragment;
import com.example.zbj.baseframework.view.PersonFragment;

/**
 * Created by zbj on 2017/3/9.
 */

public class HomePageAdapter extends FragmentPagerAdapter {
    Fragment homeFragment = null;
    Fragment imFragment = null;
    Fragment newsFragment = null;
    Fragment personFragment = null;
    BottomLayout bottomLayout = null;
    ViewPager vp = null;

    public HomePageAdapter(FragmentManager fm, BottomLayout tab) {
        super(fm);
        this.bottomLayout = tab;
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
                break;
            case 1:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                }
                fragment = newsFragment;
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
