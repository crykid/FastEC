package com.blank.art.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entry.ShopCartEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.joanzapata.iconify.widget.IconTextView;

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
        RestClient.builder()
                .url("cart/")
                .loader(getContext())
                .success(new ISuccess<ShopCartEntity>() {
                    @Override
                    public void onSuccess(ShopCartEntity response) {

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        ShopCartAdapter adapter = new ShopCartAdapter(new ShopCartDataConverter().setData(response).convert());
                        mRecyclerView.setAdapter(adapter);
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }


    @OnClick({R2.id.tv_cart_clear, R2.id.tv_cart_remove_selected, R2.id.itv_cart_selet_all})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_cart_clear) {

        } else if (id == R.id.tv_cart_remove_selected) {

        } else if (id == R.id.itv_cart_selet_all) {

        }
    }
}
