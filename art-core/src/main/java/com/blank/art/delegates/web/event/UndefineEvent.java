package com.blank.art.delegates.web.event;

import android.util.Log;

/**
 * Created by:blank
 * Created on: 2018/7/30.21:38
 * Description:
 */
public class UndefineEvent extends Event {
    private static final String TAG = "UndefineEvent";

    @Override
    public String execute(String params) {
        Log.d(TAG, "execute: " + params);

        return null;
    }
}
