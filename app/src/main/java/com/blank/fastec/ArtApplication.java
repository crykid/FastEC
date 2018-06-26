package com.blank.fastec;

import android.app.Application;

import com.blank.art.ec.icon.FontEcModule;
import com.blank.latte.app.Art;
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
                .withApiHost("http://127.0.0.1")
                .withIcon(new FontAwesomeModule())
                //引入自己的字体图标
                .withIcon(new FontEcModule())
                .configure();
    }
}
