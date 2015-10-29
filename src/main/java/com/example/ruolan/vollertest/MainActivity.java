package com.example.ruolan.vollertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 1、Volley的get和post请求方式的使用
 * <p/>
 * 2、Volley的网络请求队列建立和取消队列请求
 * <p/>
 * 3、Villey于Activity生命周期的联动
 * <p/>
 * 4、Volley的简单的二次回调封装
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Volley_Get();
        //Volley_Post();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //通过这个，可以实现Volley和Activity生命周期的联动，当activity生命周期停止的时候
        MyApplication.getHttpQueues().cancelAll("abcPost");   //cancelAll()方法通过给定的tag值，将指定的队列全部关闭
    }

    /**
     * Post方式请求数据
     */
    private void Volley_Post() {
        String url = "http://apis.juhe.cn/mobile/get?";  //利用post方式传递，不能直接传递参数，要利用getParams（）方法
      /*  StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //利用这个方法，用户在Volley中使用post方式请求数据中的参数的传递
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("phone", "18503942380");
                hashMap.put("key", "f624ddca237e160baca9d7e40a5c14a5");
                return hashMap;
            }
        };*/
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", "18503942380");
        map.put("key", "f624ddca237e160baca9d7e40a5c14a5");
        JSONObject object = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("abcPost"); //设置标签
        MyApplication.getHttpQueues().add(request);
    }

    /**
     * Get方式请求方式
     */
    private void Volley_Get() {
        String url = "http://apis.juhe.cn/mobile/get?phone=18503942380&key=f624ddca237e160baca9d7e40a5c14a5";
       /* StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("abcGet"); //设置标签
        MyApplication.getHttpQueues().add(request);*/
      /*  JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("abcGet"); //设置标签
        MyApplication.getHttpQueues().add(request);*/
        VolleyRequest.RequestGet(this, url, "abcGet", new VolleyInterface(this,VolleyInterface.sListener,VolleyInterface.sErrorListener) {
            @Override
            public void onMySucess(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMyError(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
