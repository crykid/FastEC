package com.blank.art.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entry.CategoriesEntiry;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 10:00
 * Description:商品分类页面，左边主分类列表fragment
 */

public class CategoryListDelegate extends ArtDelegate {
    @BindView(R2.id.rlv_category_list)
    RecyclerView mRecyclerView;

    @Override
    public Object getLyout() {
        return R.layout.delegate_category_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecyclerView();
        RestClient.builder()
                .url("categorys/")
                .loader(getContext())
                .success(new ISuccess<List<CategoriesEntiry>>() {
                             @Override
                             public void onSuccess(List<CategoriesEntiry> response) {
                                 final List<MultipleItemEntity> data =
                                         new CategoryListDataConverter().setData(response).convert();
                                 final CategoryListAdapter adapter = new CategoryListAdapter(data, getParentDelegate());
                                 mRecyclerView.setAdapter(adapter);
                             }
                         }
                )
                .build()
                .get();


    }
}