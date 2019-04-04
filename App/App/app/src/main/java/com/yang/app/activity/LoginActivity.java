package com.yang.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yang.app.R;
import com.yang.app.utils.DBManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {


    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.et_psd)
    EditText etPsd;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.tv_register)
    TextView tvRegister;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        dbManager = new DBManager(this);
        dbManager.openDatabase();
        dbManager.closeDatabase();
    }

    @OnClick({R.id.tv_login, R.id.tv_register, R.id.et_psd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                //登录跳转到主界面
                Intent loginIntent = new Intent(this, MainActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
