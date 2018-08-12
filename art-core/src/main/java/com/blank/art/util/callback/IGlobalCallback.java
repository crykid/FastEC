package com.blank.art.util.callback;

import android.support.annotation.NonNull;

/**
 * Created by : blank
 * Created on : 2018/8/8 at 23:29
 * Description:全局回调（跨module）
 */

public interface IGlobalCallback<T> {

    void executeCallback(@NonNull T args);
}
