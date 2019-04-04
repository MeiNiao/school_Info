package com.example.infoshool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.allure.lbanners.LMBanners;
import com.example.infoshool.Adapter.LocalImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.banners)
    LMBanners banners;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initBanner();
    }

    private void initBanner() {
        List mList = new ArrayList();
        mList.add(R.mipmap.banner1);
        mList.add(R.mipmap.banner2);
        mList.add(R.mipmap.banner3);
        banners.setAdapter(new LocalImgAdapter(this), mList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banners.clearImageTimerTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banners.stopImageTimerTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        banners.startImageTimerTask();
    }
}