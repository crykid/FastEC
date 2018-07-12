package com.blank.art.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.blank.art.entity.GoodsListEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.recycler.DataConverter;
import com.blank.art.ui.recycler.MultipleRecyclerAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;


/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:下拉刷新工具类
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = "RefreshHandler";
    private final SwipeRefreshLayout SWIPEREFRESH_LAYOUT;
    private final PagesEntity ENTITY;
    private final RecyclerView RECYCLERVIEW;
    private final DataConverter CONVERTER;

    private MultipleRecyclerAdapter mAdapter;


    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, DataConverter converter, PagesEntity entity) {
        this.SWIPEREFRESH_LAYOUT = refreshLayout;
        SWIPEREFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.ENTITY = entity;
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagesEntity());
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
        ENTITY.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .params("page", "1")
                .success(new ISuccess<GoodsListEntity>() {
                    @Override
                    public void onSuccess(GoodsListEntity response) {
                        ENTITY.setTotalPages(response.count)
                                .setPageSize(response.count / 10);
                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);

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

    @Override
    public void onLoadMoreRequested() {

    }
}
