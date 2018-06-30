package com.blank.art.retrofit.callback;

import android.os.Handler;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blank.art.ui.loader.Loader;
import com.blank.art.ui.loader.LoaderStyle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 13:51
 * Description:
 */

public class RequestCallbacks implements Callback<String> {
    private static final String TAG = "RequestCallbacks";

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;

    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();


    private final Type mType;

    /**
     * loader小时延长时间
     */
    private final int TIME_LOADER_CANCLE = 1000;

    public RequestCallbacks(IRequest mRequest, ISuccess mSuccess, IError mError, IFailure mFailure, LoaderStyle loaderStyle) {
        this.REQUEST = mRequest;
        this.SUCCESS = mSuccess;
        this.ERROR = mError;
        this.FAILURE = mFailure;
        this.LOADER_STYLE = loaderStyle;


        final Type[] types = SUCCESS.getClass().getGenericInterfaces();

        if (MethodHandler(types) == null || MethodHandler(types).size() == 0) {

        }
        mType = MethodHandler(types).get(0);

    }

    private List<Type> MethodHandler(Type[] types) {
        List<Type> needTypes = new ArrayList<>();

        for (Type paramType : types) {
            System.out.println("  " + paramType);
            if (paramType instanceof ParameterizedType) {
                Type[] parenTypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type childType : parenTypes) {
                    needTypes.add(childType);
                    if (childType instanceof ParameterizedType) {
                        Type[] childTypes = ((ParameterizedType) childType).getActualTypeArguments();
                        for (Type type : childTypes) {
                            needTypes.add(type);
                        }
                    }
                }
            }
        }
        return needTypes;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.d(TAG, "onResponse: " + response.toString());
        //如果请求成功
        if (response.isSuccessful()) {
            //如果call已经执行了
            if (call.isExecuted()) {
                if (SUCCESS != null) {

                    SUCCESS.onSuccess(JSON.parseObject(response.body(), mType));
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
            }, TIME_LOADER_CANCLE);

        }
    }
}
