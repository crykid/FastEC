package com.blank.art.delegates.web;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 15:53
 * Description:
 */

public class WebViewInitializer {
    private static final String USER_AGENT = "Art";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static WebView createWebView(WebView webView) {

        WebView.setWebContentsDebuggingEnabled(true);

        //cookie相关
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        //android 5.0以后cookie跨域默认是关闭的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }

        cookieManager.setAcceptFileSchemeCookies(true);


        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //语序截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件，主要是为了防止webview长按复制等操作
        webView.setOnLongClickListener(v -> true);

        //初始化WebSettings
        final WebSettings settings = webView.getSettings();
        //开放javascript通道
        settings.setJavaScriptEnabled(true);
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + USER_AGENT);

        //隐藏缩放空间
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);

        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);

        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }
}
