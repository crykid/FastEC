package com.blank.art.retrofit.intercepter;

import android.support.annotation.RawRes;

import com.blank.art.util.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by blank.
 * Created on 6/27/2018.
 * Description:模拟请求服务器拦截器
 * <p>通过拦截请求转而获取本地数据来实现模拟请求服务器，在开发模式下服务器开发未完成时不失是一个好的方法</p>
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        //得到我们需要拦截的url
        final String url = chain.request().url().toString();

        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("ok")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }


}
