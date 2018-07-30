package com.blank.art.retrofit.intercepter;

import android.annotation.SuppressLint;

import com.blank.art.util.storage.ArtPreference;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by:blank
 * Created on: 2018/7/30.23:55
 * Description:跨域cookie配置
 */
public class AddCookieInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(ArtPreference.getCustomAppProfile("cookie"))
                .subscribe(cookie -> {
                    //给原生API请求附带上webview拦截下来的cookie
                    builder.addHeader("Cookie", cookie);
                });

        return chain.proceed(builder.build());
    }
}
