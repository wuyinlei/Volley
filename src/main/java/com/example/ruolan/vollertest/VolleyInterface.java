package com.example.ruolan.vollertest;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by ruolan on 2015/10/29.
 */
public abstract class VolleyInterface {
    public Context mContext;   //上下文
    public static Response.Listener<String> sListener;    //请求成功方法
    public static Response.ErrorListener sErrorListener;   //请求失败的方法
    public VolleyInterface(Context context,Response.Listener<String> listener,Response.ErrorListener errorListener){
       //进行绑定
        mContext = context;
        sListener = listener;
        sErrorListener = errorListener;
    }

    //两个抽象类，请求的时候回调
    public abstract void onMySucess(String result);   //请求成功的方法   抽象的方法，没有实现
    public abstract void onMyError(VolleyError error);  //请求失败的方法

    public Response.Listener<String> loadingListener(){
        sListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //弹出加载中     在这里我们就可以灵活一些，如果请求成功，我们可以在这个地方弹出加载的进度
                onMySucess(s);
            }
        };
        return sListener;
    }

    public Response.ErrorListener errorListener(){
        sErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //弹出请求失败   如果失败了，我们弹出失败的个性方法
                onMyError(volleyError);
            }
        };
        return sErrorListener;
    }
}
