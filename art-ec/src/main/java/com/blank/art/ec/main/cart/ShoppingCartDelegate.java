package com.blank.art.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entry.ShopCartEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:购物车承载类fragment
 */
public class ShoppingCartDelegate extends BottomItemDelegate {


    @BindView(R2.id.tv_cart_clear)
    AppCompatTextView tvCartClear;
    @BindView(R2.id.tv_cart_title)
    AppCompatTextView tvCartTitle;
    @BindView(R2.id.tv_cart_remove_selected)
    AppCompatTextView tvRemoveSelected;
    @BindView(R2.id.rlv_cart_cart)
    RecyclerView mRecyclerView;
    @BindView(R2.id.itv_cart_selet_all)
    IconTextView itvSeletAll;

    @BindView(R2.id.vsbc_cart)
    ViewStubCompat vsb;


    private ShopCartAdapter mAdapter;


    private final int SELECT_MODE_ALL = 1;
    private final int SELECT_MODE_NONE = 0;


    @Override
    public Object getLyout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        itvSeletAll.setTag(0);
        RestClient.builder()
                .url("cart/")
                .loader(getContext())
                .success(new ISuccess<ShopCartEntity>() {
                    @Override
                    public void onSuccess(ShopCartEntity response) {

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mAdapter = new ShopCartAdapter(new ShopCartDataConverter().setData(response).convert());
                        mRecyclerView.setAdapter(mAdapter);
                    }
                })
                .build()
                .get();
    }

    @SuppressWarnings("unused")
    @OnClick({R2.id.tv_cart_clear, R2.id.tv_cart_remove_selected, R2.id.itv_cart_selet_all})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_cart_clear) {
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();

        } else if (id == R.id.tv_cart_remove_selected) {
            clearCartItem();

        } else if (id == R.id.itv_cart_selet_all) {
            final int SELECT_MODE = (int) itvSeletAll.getTag();
            if (SELECT_MODE == SELECT_MODE_NONE) {
                itvSeletAll.setTextColor(ContextCompat.getColor(getContext(), R.color.theme));
                itvSeletAll.setTag(SELECT_MODE_ALL);
                mAdapter.setSelectedAll(true);
            } else {
                itvSeletAll.setTextColor(ContextCompat.getColor(getContext(), R.color.wechat_black));
                itvSeletAll.setTag(SELECT_MODE_NONE);
                mAdapter.setSelectedAll(false);
            }
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    private void clearCartItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity e : data) {
            final boolean selected = e.getField(ShopCartItemFields.SELECTED);
            if (selected) {
                deleteEntities.add(e);
            }
        }

        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            if (mAdapter.getData().contains(entity)) {
                removePosition = mAdapter.getData().indexOf(entity);
                mAdapter.remove(removePosition);

                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount() - 1);
            }
        }
    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
//            vsb.
        }
    }
}
