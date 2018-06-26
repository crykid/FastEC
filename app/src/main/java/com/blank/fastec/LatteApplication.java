package com.blank.fastec;

import android.app.Application;

import com.blank.latte.app.Art;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:28
 * Description: application
 */

public class LatteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Art.init(this)
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
