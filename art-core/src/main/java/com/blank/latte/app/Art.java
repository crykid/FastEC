package com.blank.latte.app;

import android.content.Context;

import java.util.HashMap;

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

    private static HashMap<String, Object> getConfigurations() {

        return Configurator.getLatteConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigTypes.APPLICATION_CONTEXT);
    }
}
