package com.blank.art.retrofit.callback;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:46
 * Description: 请求开始和结束的回调
 */

public interface IRequest {
    void onReqestStart();

    void onRequestEnd();
}
