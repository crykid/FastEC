package com.blank.art.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.entity.GoodsListEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.recycler.BaseDecoration;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.blank.art.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:
 */
public class IndexDelegate extends BottomItemDelegate {
    private static final String TAG = "IndexDelegate";

    @BindView(R2.id.rlv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.itv_index_scan)
    IconTextView itvIndexScan;
    @BindView(R2.id.et_search_edit)
    AppCompatEditText etSearchEdit;
    @BindView(R2.id.itv_index_message)
    IconTextView itvIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;


    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object getLyout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mSwipeRefreshLayout, mRecyclerView, new IndexDataConverter());
        mRefreshHandler.firstPage("goods/");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecyclerView();
        initRefreshLayout();
//        mRefreshHandler.firstPage("goods/");
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
    }

    @OnClick({R2.id.itv_index_scan, R2.id.itv_index_message})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.itv_index_scan) {
        } else if (i == R.id.itv_index_message) {
        }
    }
}