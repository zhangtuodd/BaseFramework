package com.example.zbj.baseframework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zbj.baseframework.R;
import com.example.zbj.baseframework.bean.ZhihuList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbj on 2017/3/16.
 */

public class ZhihuDialyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List list;

    public ZhihuDialyAdapter(Context context, ArrayList<ZhihuList.Question> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_home_item, parent, false);
        return new MyVierHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyVierHolder) {
            ZhihuList.Question item = (ZhihuList.Question) list.get(position);
            String imgUrl = item.getImages().get(0);
            if(imgUrl == null){
              ((MyVierHolder) holder).imageView.setImageResource(R.mipmap.ic_launcher);
            }else{
                Glide.with(context)
                        .load(imgUrl)
                        .asBitmap()
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.mipmap.ic_launcher)
                        .centerCrop()
                        .into(((MyVierHolder)holder).imageView);
            }
            ((MyVierHolder) holder).textView.setText(item.getTitle());


        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVierHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public MyVierHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewCover);
            textView = (TextView) itemView.findViewById(R.id.textViewTitle);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }
    }
}
