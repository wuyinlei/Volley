package com.example.ruolan.vollertest;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ruolan on 2015/10/29.
 */
public class MyApplication extends Application{

    public static RequestQueue sQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        sQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues(){
        return sQueue;
    }
}
