package com.blank.art.delegates.web.client;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blank.art.delegates.web.WebDelegate;
import com.blank.art.delegates.web.route.Router;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 15:19
 * Description:
 */

public class WebViewClientImpl extends WebViewClient {
    private static final String TAG = "WebViewClientImpl";
    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     * 最新的api
     *
     * @param view
     * @param request
     * @return
     */
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        return super.shouldOverrideUrlLoading(view, request);
//    }

    /**
     * <p>webview中页面的跳转，JavaScript的herf全都由原生接管</p>
     * <P>低版本使用，一般情况下重写这个方法，也可以根据机型、版本选择</P>
     *
     * @param view
     * @param url
     * @return true-我们接管，fasle-webview处理
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        Log.d(TAG, "shouldOverrideUrlLoading: " + url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }
}
