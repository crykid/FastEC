package com.blank.art.delegates.web;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blank.art.app.Art;
import com.blank.art.delegates.IPageLoadListener;
import com.blank.art.delegates.web.chromeclient.WebChromeClientImpl;
import com.blank.art.delegates.web.client.WebViewClientImpl;
import com.blank.art.delegates.web.route.RouteKeys;
import com.blank.art.delegates.web.route.Router;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 15:12
 * Description: 一个h5页面绑定（套壳）一个webdelegate
 */

public class WebDelegateImpl extends BaseWebDelegate {


    private IPageLoadListener mPageLoadListener = null;

    private static final Handler HANDLER = Art.getHandler();

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public Object getLyout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());

        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return WebViewInitializer.createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.mPageLoadListener = pageLoadListener;
    }
}
