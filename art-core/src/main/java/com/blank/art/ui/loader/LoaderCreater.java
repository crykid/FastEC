package com.blank.art.ui.loader;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 14:21
 * Description:
 * <p>
 * 官方的用法每次使用时使用反射的方法获取对应的inicator，是一种性能的浪费，这里直接创建好并存储好，以便直接使用
 * </p>
 */

public final class LoaderCreater {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 通过反射，根据type（LoaderStyle）获取对应的类型的实例
     *
     * @param name
     * @return
     */
    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //传入了整个类名
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();

            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".")
            ;
        }
        drawableClassName.append(name);

        try {
            final Class<?> drwableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drwableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
