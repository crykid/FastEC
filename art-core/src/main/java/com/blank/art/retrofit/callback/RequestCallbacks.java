package com.blank.art.retrofit.callback;

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

    public RequestCallbacks(IRequest mRequest, ISuccess mSuccess, IError mError, IFailure mFailure) {
        this.REQUEST = mRequest;
        this.SUCCESS = mSuccess;
        this.ERROR = mError;
        this.FAILURE = mFailure;
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
//        if (REQUEST != null) {
//            REQUEST.onRequestEnd();
//        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }
}
