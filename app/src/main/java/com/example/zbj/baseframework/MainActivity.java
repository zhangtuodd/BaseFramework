package com.example.zbj.baseframework;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.zbj.baseframework.adapter.HomePageAdapter;
import com.example.zbj.baseframework.view.BottomLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp;
    private BottomLayout tab;
    private HomePageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = (BottomLayout) findViewById(R.id.bottom_tab);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(4);
        tab.set(vp);
        pageAdapter = new HomePageAdapter(getSupportFragmentManager(),tab);
        vp.setAdapter(pageAdapter);
//        tab.setPicture(0);
        vp.addOnPageChangeListener(pageAdapter.set());

    }
}
