package com.blank.art.retrofit.intercepter;

import android.support.annotation.RawRes;

import com.blank.art.util.storage.ArtPreference;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by blank.
 * Created on 6/27/2018.
 * Description:
 */

public class BaseInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Authorization", "JWT " + ArtPreference.getToken())
                .build();
        return chain.proceed(request);
    }

    /**
     * 使用有序Map获取所有的请求参数
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        //请求参数个数
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 使用有序Map获取form中的参数
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     * 从form中获取单个请求字段值
     *
     * @param chain
     * @param key
     * @return
     */
    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
