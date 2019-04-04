package com.yang.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allure.lbanners.LMBanners;
import com.yang.app.R;
import com.yang.app.adapter.LocalImgAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.banners)
    LMBanners banners;
    @InjectView(R.id.lin_chapterexericse)
    LinearLayout linChapterexericse;
    @InjectView(R.id.lin_test)
    LinearLayout linTest;
    @InjectView(R.id.lin_history)
    LinearLayout linHistory;
    @InjectView(R.id.lin_collect)
    LinearLayout linCollect;
    @InjectView(R.id.lin_error)
    LinearLayout linError;
    @InjectView(R.id.lin_statics)
    LinearLayout linStatics;
    @InjectView(R.id.imageView2)
    ImageView imageView2;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.lin_updatapasswd)
    LinearLayout linUpdatapasswd;
    @InjectView(R.id.lin_message)
    LinearLayout linMessage;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initBanner();
    }

    //    初始化banner图
    private void initBanner() {
        /**
         * 第一个参数是我们的适配器，第二个参数是我们传入的图片
         */
        List mList = new ArrayList();
        mList.add(R.drawable.banner1);
        mList.add(R.drawable.banner2);
        mList.add(R.drawable.banner3);
        banners.setAdapter(new LocalImgAdapter(this), mList);
    }

    //停止事件,节省资源
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banners.clearImageTimerTask();
    }

    @OnClick({R.id.banners, R.id.lin_chapterexericse, R.id.lin_test, R.id.lin_history, R.id.lin_collect, R.id.lin_error, R.id.lin_statics, R.id.imageView2, R.id.textView, R.id.lin_updatapasswd, R.id.lin_message, R.id.activity_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.banners:
                break;
            case R.id.lin_chapterexericse:
                Intent chapterexericseIntent = new Intent(this, ChapterexericseActivity.class);
                startActivity(chapterexericseIntent);
                break;
            case R.id.lin_test:
                break;
            case R.id.lin_history:
                break;
            case R.id.lin_collect:
                break;
            case R.id.lin_error:
                break;
            case R.id.lin_statics:
                break;
            case R.id.imageView2:
                break;
            case R.id.textView:
                break;
            case R.id.lin_updatapasswd:
                break;
            case R.id.lin_message:
                break;
            case R.id.activity_main:
                break;
        }
    }
}
