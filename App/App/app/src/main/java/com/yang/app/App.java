package com.yang.app;

import com.blankj.utilcode.util.Utils;

import org.litepal.LitePalApplication;

/**
 * Created by dell on 2017/12/19.
 */

public class App extends LitePalApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
