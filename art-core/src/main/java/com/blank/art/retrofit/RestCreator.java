package com.blank.art.retrofit;

import com.blank.art.app.Art;
import com.blank.art.app.ConfigTypes;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:27
 * Description:
 */

public class RestCreator {


    private static final class ParamHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamHolder.PARAMS;
    }




    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }




    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                //仅仅在reqest时候调用
//                .addInterceptor()
                //在request和response分别调用
//                .addNetworkInterceptor()
                .build();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Art.getConfigurations().get(ConfigTypes.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();

    }




}
