package com.blank.art.retrofit;

import com.blank.art.app.Art;
import com.blank.art.app.ConfigTypes;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by : blank
 * Created on : 2018/6/27 at 10:27
 * Description: 创建Retrofit和OkHttp的单例
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
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = (ArrayList<Interceptor>) Art.getConfigurations().get(ConfigTypes.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && INTERCEPTORS.size() > 0) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT =
                addInterceptor()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        //仅仅在reqest时候调用
//                .addInterceptor()
                        //在request和response分别调用
//                .addNetworkInterceptor()
                        .build();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Art.getConfigurations().get(ConfigTypes.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();

    }




}
