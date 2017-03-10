package com.example.zbj.baseframework.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zbj.baseframework.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbj on 2017/3/8.
 */

public class BottomLayout extends LinearLayout {
    private ViewPager vp = null;
    private List<BottomView> tabList = new ArrayList<>();
    private String[] tabText = {"首页", "消息", "个人", "IM"};
    private int[] defImage = {R.mipmap.home_default_button, R.mipmap.community_default_button, R.mipmap.my_default_button, R.mipmap.talk_default_button};
    private int[] actImage = {R.mipmap.home_active_button, R.mipmap.community_active_button, R.mipmap.my_active_button, R.mipmap.talk_active_button};

    public BottomLayout(Context context) {
        super(context);
        initView(context);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void set(ViewPager vp) {
        this.vp = vp;
    }


    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        for (int i = 0; i < 4; i++) {
            final int a = i;
            final BottomView bv = new BottomView(context);
            bv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
            bv.setGravity(Gravity.CENTER);
            bv.textView.setText(tabText[i]);
            bv.textView.setTextColor(getResources().getColor(R.color.text_orange));
            bv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setPicture(a);
                    vp.setCurrentItem(a);
                }
            });
            tabList.add(bv);
            this.addView(bv);
        }
        setPicture(0);
    }

    public void setPicture(int i) {

        switch (i) {
            case 0:
                setValue(0);
                break;
            case 1:
                setValue(1);
                break;
            case 2:
                setValue(2);
                break;
            case 3:
                setValue(3);
                break;
            default:
                break;
        }
    }

    private void setValue(int i) {
        for (int j = 0; j < 4; j++) {
            tabList.get(j).imageView.setBackgroundResource(defImage[j]);
            tabList.get(j).textView.setTextColor(getResources().getColor(R.color.text_default));
        }
        tabList.get(i).imageView.setBackgroundResource(actImage[i]);
        tabList.get(i).textView.setTextColor(getResources().getColor(R.color.text_orange));

    }


}
