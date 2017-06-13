package com.example.girls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static com.example.girls.R.id.send_request;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";//logt
    Button btn;
    TextView responseText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == send_request) {
            sendRequestWithOkHttp();//使用OkHttp发送网络请求
            btn.setVisibility(View.GONE);
        }
    }



    //发送网络请求
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://gank.io/api/data/福利/10/2")//每次请求的个数：10    请求的是第几页的数据：2
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);//通过UI显示服务器返回的数据
                    parseJSONWithGSON(responseData);//使用Gson解析服务器返回的数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




    //Gson解析,Girls是根节点
    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        Girls girls = gson.fromJson(jsonData,new TypeToken<Girls>(){}.getType());

        // Log.d("MainActivity", girls.toString());//结合JavaBean返回的字符串，打印所有数据
        for(Results results:girls.getResultsList()){

            //选择只打印图片
            Log.d(TAG, results.getUrl());//ldg+tab键

        }

    }



    //切换到主线程进行UI操作,Android不允许在子线程中进行UI操作。将服务器返回的数据显示在ListView上
    private void showResponse(final String response) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }



}





/*
    //使用HttpURLConnection发送网络请求
    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://gank.io/api/data/福利/10/1");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        showResponse(response.toString());//显示服务器返回的数据
                        parseJSONWithGSON(response.toString());//使用Gson解析服务器返回的数据
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
*/
