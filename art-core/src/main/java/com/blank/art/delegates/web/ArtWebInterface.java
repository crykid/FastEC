package com.blank.art.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.blank.art.delegates.web.event.Event;
import com.blank.art.delegates.web.event.EventManager;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 14:59
 * Description:webView与原生交互的接口
 */

public class ArtWebInterface {
    private final BaseWebDelegate DELEGATE;

    private ArtWebInterface(BaseWebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static ArtWebInterface create(BaseWebDelegate delegate) {
        return new ArtWebInterface(delegate);
    }

    /**
     * Android4.4以后必须添加的注解
     *
     * @param params
     * @return
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");

        final Event event = EventManager.getInstance().createEvent(action);

        if (event != null) {

            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());

            return event.execute(params);
        }

        return null;
    }
}
