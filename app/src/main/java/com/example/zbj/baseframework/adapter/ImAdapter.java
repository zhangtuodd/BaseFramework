package com.example.zbj.baseframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.zbj.baseframework.view.ContectFragmentIMSub;
import com.example.zbj.baseframework.view.SystemFragmentIMSub;
import com.example.zbj.baseframework.view.TransactionFragmentIMSub;

/**
 * Created by zbj on 2017/4/1.
 */

public class ImAdapter extends FragmentPagerAdapter  {
    private String[] mTitles;
    private ContectFragmentIMSub contectFragment;
    private TransactionFragmentIMSub transactionFragment;
    private SystemFragmentIMSub systemFragment;

    public ImAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                if(contectFragment == null){
                    contectFragment = new ContectFragmentIMSub();
                }
                fragment = contectFragment;
                break;
            case 1:
                if(transactionFragment == null){
                    transactionFragment = new TransactionFragmentIMSub();
                }
                fragment = transactionFragment;
                break;
            case 2:
                if(systemFragment == null){
                    systemFragment = new SystemFragmentIMSub();
                }
                fragment = systemFragment;
                break;
            default:
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    public ViewPager.OnPageChangeListener pageChangeListener(){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
