package com.blank.fastec.event;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;
import android.widget.Toast;

import com.blank.art.delegates.web.event.Event;

/**
 * Created by:blank
 * Created on: 2018/7/30.21:51
 * Description:js测试事件
 * <p>在这个事件中，模拟实现：</p>
 * <p>点击h5控件
 *  ·······--->h5（js）调用原生
 *  ······················---> 原生调用js的“nativeCall（）”方法
 *  ····················································---> 原生拦截了js的alert，转而调用原生的事件</p>
 */
public class TestEvent extends Event {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(() -> {
                webView.evaluateJavascript("nativeCall();", null);
            });
        }
        return null;
    }
}
