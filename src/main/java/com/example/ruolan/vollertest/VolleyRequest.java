package com.example.ruolan.vollertest;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by ruolan on 2015/10/29.
 */
public class VolleyRequest {

    public static StringRequest sStringRequest;
    public static Context sContext;

    /**
     * 自定义的get
     *
     * @param context
     * @param url
     * @param tag
     * @param vif
     */
    public static void RequestGet(Context context, String url, String tag, VolleyInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag);//在请求之前全部取消掉,防止消耗内存
        //StringRequest()这个方法，四个参数是在上一篇有介绍的
        //vif.loadingListener()、vif.errorListener()这两个是请求成功，请求失败的两个抽象方法，我们来定义一个接口来实现
        sStringRequest = new StringRequest(Request.Method.GET, url, vif.loadingListener(), vif.errorListener());
        sStringRequest.setTag(tag);   //设置Tag标签
        MyApplication.getHttpQueues().add(sStringRequest); //将请求添加到队列里面
        MyApplication.getHttpQueues().start();   //调用start()方法
    }

    /**
     * 自定义的post
     * @param context
     * @param url
     * @param tag
     * @param params
     * @param vif
     */
    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params, VolleyInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag);//在请求之前全部取消掉
        sStringRequest = new StringRequest(url,vif.loadingListener(),vif.errorListener()){
            @Override

            //同样我们使用getParams()来传递
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        sStringRequest.setTag(tag);  //设置Tag
        MyApplication.getHttpQueues().add(sStringRequest);  //加入请求队列
        MyApplication.getHttpQueues().start();   //调用start()方法启动
    }
}
