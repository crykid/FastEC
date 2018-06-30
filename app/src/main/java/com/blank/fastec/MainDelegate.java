package com.blank.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 18:08
 * Description:
 */

public class MainDelegate extends ArtDelegate {
    private static final String TAG = "MainDelegate";

    @Override
    public Object getLyout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        clientTest();

    }

    private void clientTest() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .request(new IRequest() {
                    @Override
                    public void onReqestStart() {
                        Log.d(TAG, "onReqestStart: ");
                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onSuccess: " + response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.d(TAG, "onFailure: ");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
//                        Log.d(TAG, "onError: code=" + code + " message:" + message);
                    }
                })
//                .dir("")
//                .extension("")
//                .name("")
                .build()
//                .downLoad();
                .get();
    }
}
