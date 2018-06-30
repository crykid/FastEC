package com.blank.art.retrofit.callback;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:44
 * Description:请求成功的回调
 */

public interface ISuccess<T> {

    void onSuccess(T response);
}
