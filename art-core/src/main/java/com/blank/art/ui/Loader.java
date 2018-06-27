package com.blank.art.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.blank.art.R;
import com.blank.art.util.DemenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 14:36
 * Description: 加载进度 工具
 */

public class Loader {
    /**
     * 缩放比，让loader根据屏幕的大小调整自己的大小
     */
    private static final int LOADER_SIZE_SCALE = 8;
    /**
     * 偏移量
     */
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    /**
     * 包含默认的loader样式
     *
     * @param context 当前上下文，不建议全局上下文，哪里使用，传递哪里的上下文
     */
    public static void showLoading(Context context) {
        showLoading(context, LoaderStyle.BallTrianglePathIndicator);
    }

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreater.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DemenUtil.getScreenWidth();
        int deviceHeight = DemenUtil.getScreenHeight();

        final Window dialogWidow = dialog.getWindow();

        if (dialogWidow != null) {
            WindowManager.LayoutParams lp = dialogWidow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            //设置偏移量
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }


    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();

                }
            }
        }
    }
}
