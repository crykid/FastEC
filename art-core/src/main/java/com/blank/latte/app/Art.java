package com.blank.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 10:27
 * Description:
 */

public final class Art {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigTypes.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String, Object> getConfigurations() {

        return Configurator.getLatteConfigs();
    }
}
