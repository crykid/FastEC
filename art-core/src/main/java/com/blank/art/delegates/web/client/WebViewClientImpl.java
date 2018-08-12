package com.blank.art.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blank.art.app.Art;
import com.blank.art.app.ConfigTypes;
import com.blank.art.delegates.IPageLoadListener;
import com.blank.art.delegates.web.BaseWebDelegate;
import com.blank.art.delegates.web.route.Router;
import com.blank.art.ui.loader.Loader;
import com.blank.art.util.storage.ArtPreference;


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

    /**
     * 同步跨域cookie
     * <p>
     * 获取浏览器的cookie，并存储到本地，然后配置到原生的API请求中
     * {@link com.blank.art.retrofit.intercepter.AddCookieInterceptor}
     * </p>
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
        注意，这里的cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String WEB_HOST = Art.getConfiguration(ConfigTypes.WEB_HOST);
        //取出webhost对应的cookie
        if (WEB_HOST != null) {
            //如果有cookie，则取出cookie存放到本地，
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(WEB_HOST);
                if (!TextUtils.isEmpty(cookieStr)) {

                    ArtPreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(() -> Loader.stopLoading(), 1000);

    }
}
