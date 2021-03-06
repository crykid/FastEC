package com.blank.art.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 16:22
 * Description: 处理webview内部（js）页面的事件
 */

public class WebChromeClientImpl extends WebChromeClient {

    /**
     * 拦截js对话框，调用原生对话框
     *
     * @param view
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
