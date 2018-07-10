package com.blank.art.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:27
 * Description: 对外工具类
 */

public final class Art {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigTypes.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {

        return Configurator.getConfigs();
    }

    public static Configurator getConfigurator() {

        return Configurator.getInstance();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigTypes.APPLICATION_CONTEXT.name());
    }
}
