package com.yang.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yang.app.R;
import com.yang.app.javaBean.Answer;

import org.litepal.crud.DataSupport;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void litepal(View view) {
        //全查
        List<Answer> list = DataSupport.findAll(Answer.class);
        for (int i = 0; i < list.size(); i++) {
            Log.i("=======================", list.get(i).toString());
        }
    }
}
