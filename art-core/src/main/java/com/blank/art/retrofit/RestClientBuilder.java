package com.blank.art.retrofit;

import android.content.Context;

import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.LoaderStyle;

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

    private String mUrl = null;

    private static final WeakHashMap<String, Object> mParams = RestCreator.getParams();

    private IRequest mRequest = null;

    private ISuccess mSuccess = null;

    private IError mError = null;

    private IFailure mFailure = null;

    private RequestBody mBody = null;

    private LoaderStyle mLoaderstyle = null;
    private Context mContext = null;


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

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderstyle = LoaderStyle.BallTrianglePathIndicator;
        return this;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle loaderstyle) {
        this.mContext = context;
        this.mLoaderstyle = loaderstyle;
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
        return new RestClient(mUrl, mParams, mRequest, mSuccess, mError, mFailure, mBody, mContext, mLoaderstyle);
    }


}
