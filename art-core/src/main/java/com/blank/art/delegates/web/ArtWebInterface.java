package com.blank.art.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 14:59
 * Description:webView与原生交互的接口
 */

public class ArtWebInterface {
    private final WebDelegate DELEGATE;

    private ArtWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static ArtWebInterface create(WebDelegate database) {
        return new ArtWebInterface(database);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
