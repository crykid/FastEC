package com.blank.fastec;

import android.app.Application;

import com.blank.art.ec.database.DatabaseManager;
import com.blank.art.ec.icon.FontEcModule;
import com.blank.art.app.Art;
import com.blank.art.retrofit.intercepter.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:28
 * Description: application
 */

public class ArtApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Art.init(this)
                .withApiHost("http://192.168.1.197:8000/")
                //引入默认的iconfont
                .withIcon(new FontAwesomeModule())
                //引入自己的字体图标
                .withIcon(new FontEcModule())
                //取到了res-raw-rest.json文件
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
