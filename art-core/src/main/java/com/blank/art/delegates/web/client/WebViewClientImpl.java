package com.blank.art.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blank.art.app.Art;
import com.blank.art.delegates.IPageLoadListener;
import com.blank.art.delegates.web.BaseWebDelegate;
import com.blank.art.delegates.web.route.Router;
import com.blank.art.ui.loader.Loader;


/**
 * Created by : blank
 * Created on : 2018/7/30 at 15:19
 * Description:处理webview外部的事件
 */

public class WebViewClientImpl extends WebViewClient {
    private static final String TAG = "WebViewClientImpl";
    private final BaseWebDelegate DELEGATE;

    private IPageLoadListener mPageLoadListener = null;

    private static final Handler HANDLER = Art.getHandler();

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.mPageLoadListener = pageLoadListener;
    }

    public WebViewClientImpl(BaseWebDelegate delegate) {
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

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        //页面打开时候执行的回调
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadStart();
        }
        Loader.showLoading(view.getContext());

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(() -> Loader.stopLoading(), 1000);

    }
}
