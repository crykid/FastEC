package com.blank.art.ec.main.user.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entity.OrderListEntity;
import com.blank.art.ec.main.user.UserDelegate;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;


/**
 * Created by blank.
 * Created on 8/06/2018.
 * Description:订单列表
 */

public class OrderListDelegate extends ArtDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object getLyout() {
        return R.layout.delegate_order;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(UserDelegate.ORDER_TYPE);
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list/")
                .params("type", mType)
                .success(new ISuccess<OrderListEntity>() {
                    @Override
                    public void onSuccess(OrderListEntity response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setData(response.orders).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}
