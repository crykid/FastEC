package com.blank.art.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 14:04
 * Description:
 */

public abstract class WebDelegate extends ArtDelegate implements IWebViewInitializer {

    private WebView mWebView = null;

    private final ReferenceQueue<WebView> WEBVIEW_QUEUE = new ReferenceQueue<>();

    private String mUrl = null;
    //WebView是否可用
    private boolean mIsWebViewAvailable = false;

    public WebDelegate() {
    }

    /**
     * 强制调用抽象类初始化
     *
     * @return
     */
    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();

    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        //防止webView重复初始化造成内存泄漏
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                //webView以new的形式初始化，在xml中直接使用易造成内存泄漏
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<WebView>(new WebView(getContext()), WEBVIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(ArtWebInterface.create(this), "ART");
                //webView初始化完成
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("WebView is null!");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
