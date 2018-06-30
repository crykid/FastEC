package com.blank.art.retrofit;

import android.content.Context;

import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.LoaderStyle;

import java.io.File;
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

    private File mFile = null;

    private LoaderStyle mLoaderstyle = null;
    private Context mContext = null;

    //文件下载相关
    private String mDownloadDir;
    private String mExtension;
    private String mName;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 添加多个请求参数
     *
     * @param params
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        checkParams();
        this.mParams.putAll(params);
        return this;
    }

    /**
     * 添加单个请求参数
     *
     * @param key   参数字段
     * @param value 字段值
     * @return
     */
    public final RestClientBuilder params(String key, Object value) {
        checkParams();
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {

        this.mFile = new File(filePath);
        return this;
    }

    /**
     * 添加文件下载目录
     *
     * @param dowloadDir 文件下载目录
     * @return
     */
    public final RestClientBuilder dir(String dowloadDir) {

        this.mDownloadDir = dowloadDir;
        return this;
    }

    /**
     * 文件名称前缀
     *
     * @param extension
     * @return
     */
    public final RestClientBuilder extension(String extension) {

        this.mExtension = extension;
        return this;
    }

    /**
     * 文件名称
     *
     * @param fileName
     * @return
     */
    public final RestClientBuilder name(String fileName) {

        this.mName = fileName;
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 设置loader(进度显示）
     *
     * @param context
     * @return
     */
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

    public final RestClient build() {
        return new RestClient(
                mUrl, mParams,
                mDownloadDir, mExtension, mName,
                mRequest, mSuccess, mError, mFailure,
                mBody,
                mFile,
                mContext,
                mLoaderstyle);
    }


}
