package com.blank.art.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.delegates.web.BaseWebDelegate;
import com.blank.art.delegates.web.WebDelegateImpl;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 15:23
 * Description:
 */

public class Router {

    private Router() {
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(BaseWebDelegate delegate, String url) {
        //如果包含电话协议
        if (url.contains("tel:")) {
            makePhoneCall(delegate.getContext(), url);
            return true;
        }

        final ArtDelegate parentDelegate = delegate.getTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        if (parentDelegate == null) {
            delegate.start(webDelegate);
        } else {
            parentDelegate.start(webDelegate);
        }
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(BaseWebDelegate delegate, String url) {
        if (delegate != null) {
            loadPage(delegate.getWebView(), url);
        } else {
            throw new NullPointerException("BaseWebDelegate is null!");
        }
    }

    private void makePhoneCall(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
//        context.startActivity(intent);
        ContextCompat.startActivity(context, intent, null);

    }
}
