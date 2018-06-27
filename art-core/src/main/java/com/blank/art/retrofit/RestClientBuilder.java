package com.blank.art.retrofit;

import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:43
 * Description:
 */

public class RestClientBuilder {

    private String mUrl;

    private static final WeakHashMap<String, Object> mParams = RestCreator.getParams();

    private IRequest mRequest;

    private ISuccess mSuccess;

    private IError mError;

    private IFailure mFailure;

    private RequestBody mBody;


    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        this.mParams.put(key, value);
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    private Map<String, Object> checkParams() {
        if (mParams == null) {
            return new WeakHashMap<>();
        }
        return mParams;
    }

    //String URL, Map<String, Object> PARAMS, IRequest REQUEST, ISuccess SUCCESS, IError ERROR, IFailure FAILURE, RequestBody BODY)
    public final RestClient build() {
        return new RestClient(mUrl, mParams, mRequest, mSuccess, mError, mFailure, mBody);
    }


}
