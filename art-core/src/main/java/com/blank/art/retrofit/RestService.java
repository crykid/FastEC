package com.blank.art.retrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:16
 * Description:
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @POST
    @FormUrlEncoded
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);


    @PUT()
    @FormUrlEncoded
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @DELETE
    Call<String> delete(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * retrofit download默认下载文件时候先下载到内存再存储到文件，
     * 添加@Streaming注解实现边下载边写入文件系统
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @FieldMap Map<String, Object> params);

    @Multipart
    @POST
    Call<String> upLoad(@Url String url, @Part MultipartBody.Part file);
}
