package com.example.zbj.baseframework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zbj.baseframework.R;

/**
 * Created by zbj on 2017/3/8.
 */

public class BottomView extends RelativeLayout {
   public ImageView imageView;
   public TextView textView;

    public BottomView(Context context) {
        super(context);
        init(context);
    }

    public BottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        RelativeLayout layout = (RelativeLayout) View.inflate(context, R.layout.view_tab_bottom, null);
        this.addView(layout);
        imageView = (ImageView) layout.findViewById(R.id.tab_image);
        textView = (TextView) layout.findViewById(R.id.tab_text);
    }

}
