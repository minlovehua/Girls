package com.example.girls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.girls.R.id.recycler_view;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";//logt
    String url;
    Girls girls;
    RecyclerView recyclerView;
    ImageView imageView;
    private Results[] resultses;
    private List<Results> girlList = new ArrayList<>();
    private GirlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "http://gank.io/api/data/福利/10/2";//每次请求的个数：10    请求的是第几页的数据：2
        sendRequestWithOkHttp(url);//发送网络请求并解析数据
        imageView = (ImageView) findViewById(R.id.girls_image);
    }


    //发送网络请求
    public void sendRequestWithOkHttp(final String address) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String responseData = null;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(address)//每次请求的个数：10    请求的是第几页的数据：2
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    responseData = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parseJSONWithGSON(responseData);//使用Gson解析服务器返回的数据
            }
        }).start();
    }


    public void parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        girls = gson.fromJson(responseData, new TypeToken<Girls>() {
        }.getType());


        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Results results : girls.getResultsList()) {
                    //Log.d(TAG, results.getUrl());
                    girlList.add( new Results(results.getDesc(),results.getUrl()));
                }
                recyclerView = (RecyclerView) findViewById(recycler_view);
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new GirlAdapter(girlList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}


