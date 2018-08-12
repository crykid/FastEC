package com.blank.art.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.delegates.web.BaseWebDelegate;

/**
 * Created by:blank
 * Created on: 2018/7/30.21:31
 * Description: js事件
 */
public abstract class Event implements IEvent {

    private Context mContext = null;
    /**
     * 事件内容
     */
    private String mAction = null;
    private BaseWebDelegate mDelegate = null;
    private String mUrl = null;


    public Context getContext() {
        return mContext;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public ArtDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(BaseWebDelegate delegate) {
        this.mDelegate = delegate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
