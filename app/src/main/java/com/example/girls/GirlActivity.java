package com.example.girls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by 媚敏 on 2017/6/27.
 */



//点击放大步骤2
public class GirlActivity extends AppCompatActivity {
    private static final String TAG = "GirlActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);

        //通过Intent获取到图片的url
        Intent intent = getIntent();
        String girlImageId = intent.getStringExtra("abc");
        Log.d(TAG, "当前的URL是"+girlImageId);

        ImageView girlImageView = (ImageView) findViewById(R.id.girl_image_view);
        Glide.with(this).load(girlImageId).into(girlImageView);

    }
}
