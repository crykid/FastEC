package com.blank.art.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.blank.art.entity.GoodsListEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.ISuccess;


/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:下拉刷新工具类
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "RefreshHandler";
    private final SwipeRefreshLayout SWIPEREFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        this.SWIPEREFRESH_LAYOUT = refreshLayout;
        SWIPEREFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        SWIPEREFRESH_LAYOUT.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SWIPEREFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .params("page", "1")
                .success(new ISuccess<GoodsListEntity>() {
                    @Override
                    public void onSuccess(GoodsListEntity response) {
                        Log.d(TAG, "onSuccess: " + response.toString());
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
                        Log.d(TAG, "onError: ");
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
