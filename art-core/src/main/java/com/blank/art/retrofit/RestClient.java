package com.blank.art.retrofit;

import android.content.Context;

import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.retrofit.callback.RequestCallbacks;
import com.blank.art.retrofit.download.DownloadHandler;
import com.blank.art.ui.loader.Loader;
import com.blank.art.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    private final WeakHashMap<String, Object> PARAMS;

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailure FAILURE;

    private final RequestBody BODY;

    private final LoaderStyle LOADER_STYLE;

    private final File FILE;
    private final Context CONTEXT;

    /**
     * 文件下载相关
     */
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(
            String url,
            WeakHashMap<String, Object> params,
            String downloaddir,
            String extension,
            String name,
            IRequest request,
            ISuccess success,
            IError error,
            IFailure failure,
            RequestBody body,
            File file,
            Context context,
            LoaderStyle loaderStyle) {

        this.URL = url;
//        if (!this.PARAMS.isEmpty()) {
//            this.PARAMS.clear();
//        }
        this.PARAMS = params;
        this.DOWNLOAD_DIR = downloaddir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.FILE = file;
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
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = SERVICE.upLoad(URL, body);
                break;
            case PUT_RAW:
                call = SERVICE.putRaw(URL, BODY);
                break;
            case POST_RAW:
                call = SERVICE.postRaw(URL, BODY);
                break;
            default:
                break;
        }
//        call.request().newBuilder()
//                .header("Authorization", "JWT " + ArtPreference.getToken())
//                .build();

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }


    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE
        );
    }

    /**
     * get请求
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     * post/postRaw请求
     */
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put/putRaw
     */
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null !");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete请求
     */
    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void downLoad() {
        new DownloadHandler(URL, REQUEST, SUCCESS, ERROR, FAILURE, DOWNLOAD_DIR, EXTENSION, NAME).handlerDownload();
    }
}
