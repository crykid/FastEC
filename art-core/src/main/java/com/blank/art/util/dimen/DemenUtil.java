package com.blank.art.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.blank.art.app.Art;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 14:46
 * Description:
 */

public class DemenUtil {

    /**
     * 获取屏幕尺寸
     *
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Art.getApplicationContext().getResources();

        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Art.getApplicationContext().getResources();

        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
