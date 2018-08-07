package com.blank.art.util.camera;

import android.net.Uri;

import com.blank.art.delegates.PermissionCheckerDelegate;
import com.blank.art.util.file.FileUtil;

/**
 * Created by : blank
 * Created on : 2018/8/7 at 22:38
 * Description:
 */

public class ArtCamera {

    public static Uri createCropFile() {
        return Uri.parse(
                FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath()
        );
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
