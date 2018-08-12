package com.blank.art.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 14:50
 * Description:初始化webview通用接口
 */

public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    /**
     * 针对浏览器
     *
     * @return
     */
    WebViewClient initWebViewClient();

    /**
     * 针对浏览器内部页面
     *
     * @return
     */
    WebChromeClient initWebChromeClient();

}
