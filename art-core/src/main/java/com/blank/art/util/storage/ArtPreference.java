package com.blank.art.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.blank.art.app.Art;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 10:42
 * Description: SharedPreference工具
 */

public class ArtPreference {

    /**
     * 缓存名字
     */
    private static final String APP_PREFERENCES_KEY = "profile";
    private static final String PREFERENCES_KEY_TOKEN = "jwt_token";
    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Art.getApplicationContext());

    private static final SharedPreferences getAppPreference() {
        return PREFERENCES;
    }


    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }


    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppProfile() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    /**
     * app是否第一次启动
     *
     * @param key
     * @param flag
     */
    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    /**
     * app是否第一次启动
     *
     * @param key
     */
    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString("key", "");
    }

    public static String getToken() {
        return getAppPreference().getString(PREFERENCES_KEY_TOKEN, "");
    }

    public static void setToken(String token) {
        getAppPreference()
                .edit()
                .putString(PREFERENCES_KEY_TOKEN, token)
                .apply();
    }
}
