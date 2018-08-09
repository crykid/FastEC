package com.blank.art.util.callback;

import java.util.WeakHashMap;

/**
 * Created by : blank
 * Created on : 2018/8/8 at 23:28
 * Description:全局回调管理(跨module)
 */

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALBACKS = new WeakHashMap<>();

    private static class Holder {
        private final static CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALBACKS.get(tag);
    }
}
