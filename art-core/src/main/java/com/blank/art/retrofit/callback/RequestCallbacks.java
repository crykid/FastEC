package com.blank.art.retrofit.callback;

import android.os.Handler;

import com.blank.art.ui.Loader;
import com.blank.art.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 13:51
 * Description:
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest mRequest, ISuccess mSuccess, IError mError, IFailure mFailure, LoaderStyle loaderStyle) {
        this.REQUEST = mRequest;
        this.SUCCESS = mSuccess;
        this.ERROR = mError;
        this.FAILURE = mFailure;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        //如果请求成功
        if (response.isSuccessful()) {
            //如果call已经执行了
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Loader.stopLoading();
                }
            }, 1000);

        }
    }
}
