package com.example.girls;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 媚敏 on 2017/6/26.
 */

public class HttpUtil {

    public static void sendRequestWithOkHttp(final String address, okhttp3.Callback callback) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(address)
                        .build();
                client.newCall(request).enqueue(callback);
            }

}
