package com.blank.art.retrofit;

import android.content.Context;

import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.retrofit.callback.RequestCallbacks;
import com.blank.art.ui.Loader;
import com.blank.art.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:09
 * Description:
 */

public class RestClient {

    //finale 修饰的变量声明时候没有赋值必须在构造器中赋值
    private final String URL;

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;

    private final RequestBody BODY;

    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url, WeakHashMap<String, Object> params, IRequest request, ISuccess success, IError error, IFailure failure, RequestBody body, Context context, LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService SERVICE = RestCreator.getRestService();

        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onReqestStart();
        }
        if (LOADER_STYLE != null) {
            Loader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = SERVICE.get(URL, PARAMS);
                break;
            case POST:
                call = SERVICE.post(URL, PARAMS);
                break;
            case DELETE:
                call = SERVICE.delete(URL, PARAMS);
                break;
            case PUT:
                call = SERVICE.put(URL, PARAMS);
                break;
            case UPLOAD:
                break;
            case PUT_RAW:
                break;
            case POST_RAW:
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }


    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
