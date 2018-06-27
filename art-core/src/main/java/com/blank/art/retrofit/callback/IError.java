package com.blank.art.retrofit.callback;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:45
 * Description:请求出现异常的回调
 */

public interface IError {
    void onError(int code, String message);
}
