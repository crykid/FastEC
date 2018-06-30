package com.blank.art.retrofit.intercepter;

import android.support.annotation.RawRes;
import android.util.Log;

import com.blank.art.util.file.FileUtil;
import com.blank.art.util.storage.ArtPreference;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by blank.
 * Created on 6/27/2018.
 * Description:模拟请求服务器拦截器
 * <p>通过拦截请求转而获取本地数据来实现模拟请求服务器，在开发模式下服务器开发未完成时不失是一个好的方法</p>
 */

public class DebugInterceptor extends BaseInterceptor {
    private static final String TAG = "DebugInterceptor";
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    /**
     * @param debugUrl   需要拦截的url
     * @param debugRawId 该请求需要返回的json资源id
     */
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
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Authorization", "JWT " + ArtPreference.getToken())
                .build();
        Log.d(TAG, "intercept: "+ArtPreference.getToken());
        Log.d(TAG, "intercept: " + request.headers().size());

        return chain.proceed(request);
    }

    /**
     * 拦截到请求的情况下，将对应的返回内容包装返回
     *
     * @param chain 请求
     * @param rawId 返回内容（json数据）资源id
     * @return
     */
    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    /**
     * 构建返回体
     *
     * @param chain
     * @param json  返回内容，资源id对应的资源
     * @return
     */
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
