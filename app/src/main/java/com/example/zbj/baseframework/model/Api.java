package com.example.zbj.baseframework.model;

/**
 * Created by zbj on 2017/3/16.
 */

public class Api {
    // 过往消息
    // past posts
    // 若要查询的11月18日的消息，before后面的数字应该为20161118
    // if you want to select the posts of November 11th, the number after 'before' should be 20161118
    // 知乎日报的生日为2013 年 5 月 19 日，如果before后面的数字小于20130520，那么只能获取到空消息
    // the birthday of ZhiHuDaily is May 19th, 2013. So if the number is lower than 20130520, you will get a null value of post
    public static final String ZHIHU_HISTORY = "http://news.at.zhihu.com/api/4/news/before/";
}
