package com.blank.art.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:27
 * Description: 对外获取全局配置工具类
 */

public final class Art {

    private static Handler handler;

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigTypes.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {

        return Configurator.getInstance();
    }

    /**
     * 获取所有的配置
     *
     * @return
     */
    public static HashMap<Object, Object> getConfigurations() {

        return Configurator.getConfigs();
    }

    /**
     * 获取特定的配置
     *
     * @param type Enum ConfigTypes
     * @param <T>  返回类型
     * @return 目标配置
     */
    public static <T> T getConfiguration(Object type) {
        return getConfigurator().getConfigruation(type);
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigTypes.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfigurator().getConfigruation(ConfigTypes.HANDLER);
    }
}
