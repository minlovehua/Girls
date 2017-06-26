package com.example.girls;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.girls.R.attr.layoutManager;
import static com.example.girls.R.id.recycler_view;

public class MainActivity extends AppCompatActivity {

    String url;
    Girls girls;
    ImageView imageView;
    RecyclerView recyclerView;
    private Results[] resultses;
    private GirlAdapter adapter;
    private List<Results> girlList=new ArrayList<>();
    private final String TAG = "MainActivity";//log
    static int page = 1;//page静态才会累积
    String responseData;

    Handler handle= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "http://gank.io/api/data/福利/10/";//每次请求的个数：10    请求的是第几页的数据：2
        recyclerView = (RecyclerView) findViewById(recycler_view);

        begin(url+page);
        /**
         * 这一步必须写在onCreate()方法里面
         *给recyclerView指定布局和适配器
         */
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        adapter = new GirlAdapter(MainActivity.this, girlList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        loading();
    }


    public void begin(String adress) {
        HttpUtil.sendRequestWithOkHttp(adress, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //显示在界面上提示用户网络出错
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        parseJSONWithGSON(responseData);
                    }
                }).start();

            }
        });


    }


    public void parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        girls = gson.fromJson(responseData, new TypeToken<Girls>() {
        }.getType());

        for (Results results : girls.getResultsList()) {
            //Log.d(TAG, results.getUrl());
            girlList.add(new Results(results.getDesc(), results.getUrl()));
        }

        /**
         * 加载完之后通知Adapter数据发生了变化，
         * 否则Adapter里面的mResultList.size()为空，
         * 会报空指针异常
         */
        handle.sendEmptyMessage(1);

    }


    /**
     *实现上拉刷新
     */
    private void loading() {
        //addOnScrollListener是一个抽象类，有两个方法
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                             @Override//滚动状态变化时回调
                                             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                                 super.onScrollStateChanged(recyclerView, newState);//newState表示当前滚动状态
                                             }


                                             @Override//滚动时回调
                                             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                 super.onScrolled(recyclerView, dx, dy);
                                                 if (isEnd()){
                                                     page++;
                                                     begin(url+page);
                                                     adapter.notifyDataSetChanged();
                                                 }

                                             }
                                         }
        );
    }


    /**
     * 判断是否滑到底部
     * computeHorizontalScrollExtent()  屏幕高度
     * computeHorizontalScrollOffset()  用户滑动的高度
     * computeHorizontalScrollRange()   ResultList的高度
     */
    private Boolean isEnd(){
        if (recyclerView==null){
            return false;
        }else if (recyclerView.computeHorizontalScrollExtent()+recyclerView.computeHorizontalScrollOffset()>=recyclerView.computeHorizontalScrollRange()) {
            return true;
        }else
            return false;
    }

}

