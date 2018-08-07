package com.blank.art.util.camera;

import com.yalantis.ucrop.UCrop;

/**
 * Created by : blank
 * Created on : 2018/8/7 at 22:39
 * Description:
 */

public class RequestCode {
    public static final int REQUESTCODE_TAKE_PHOTE = 1;
    public static final int REQUESTCODE_PICK_PHOTE = 2;
    public static final int REQUESTCODE_CROP_PHOTE = UCrop.REQUEST_CROP;
    public static final int REQUESTCODE_CROP_ERROR = UCrop.RESULT_ERROR;
    public static final int REQUESTCODE_SCAN = 3;
}
