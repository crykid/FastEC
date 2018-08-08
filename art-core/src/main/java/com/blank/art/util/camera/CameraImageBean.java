package com.blank.art.util.camera;

import android.net.Uri;

/**
 * Created by : blank
 * Created on : 2018/8/7 at 22:39
 * Description: 存储一些中间值
 */

public class CameraImageBean {
    private Uri mPath = null;
    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance() {
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        this.mPath = path;
    }
}
