package com.blank.art.ec.main.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entity.ShopCartEntity;
import com.blank.art.pay.FastPay;
import com.blank.art.pay.IALiPayResult;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:购物车承载类fragment
 */
public class ShoppingCartDelegate extends BottomItemDelegate implements IALiPayResult {


    @BindView(R2.id.tv_cart_clear)
    AppCompatTextView tvCartClear;
    @BindView(R2.id.tv_cart_title)
    AppCompatTextView tvCartTitle;
    @BindView(R2.id.tv_cart_remove_selected)
    AppCompatTextView tvRemoveSelected;
    @BindView(R2.id.atv_cart_totalprice)
    AppCompatTextView atvTotalprice;
    @BindView(R2.id.atv_cart_pay)
    AppCompatTextView atvPay;
    @BindView(R2.id.rlv_cart_cart)
    RecyclerView mRecyclerView;
    @BindView(R2.id.itv_cart_selet_all)
    IconTextView itvSeletAll;

    @BindView(R2.id.vsbc_cart)
    ViewStubCompat vsbNoItem;


    private ShopCartAdapter mAdapter;


    private final int SELECT_MODE_ALL = 1;
    private final int SELECT_MODE_NONE = 0;

    private ShopCartEntity mShopCartEntity = null;


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

        initRecyclerView();
        fetchCartData();

//        RestClient.builder()
//                .url("cart/")
//                .loader(getContext())
//                .success(new ISuccess<ShopCartEntity>() {
//                    @Override
//                    public void onSuccess(ShopCartEntity response) {
//
////                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////                        mAdapter = new ShopCartAdapter(new ShopCartDataConverter().setData(response).convert());
////                        mRecyclerView.setAdapter(mAdapter);
////                        checkItemCount();
//
//
//                        //如果是加载更多
//                        mAdapter.addData(new ShopCartDataConverter().setData(response).convert());
//                        checkItemCount();
//                        //如果是刷新
////                        mAdapter.setNewData(new ShopCartDataConverter().setData(response).convert());
//                    }
//                })
//                .build()
//                .get();
    }

    @SuppressWarnings("unused")
    @OnClick({R2.id.tv_cart_clear, R2.id.tv_cart_remove_selected, R2.id.itv_cart_selet_all, R2.id.atv_cart_pay})
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
        } else if (id == R.id.atv_cart_pay) {
            FastPay.create(this).startPayDialog();

            createOrder();
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
        checkItemCount();
    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            @SuppressLint("RestrictedApi") final View stubView = vsbNoItem.inflate();
            final AppCompatTextView tvToBuy = stubView.findViewById(R.id.atv_item_cart_sub_tobuy);
            tvToBuy.setOnClickListener(v -> {
                //TODO：这里应该跳转到主页等等操作
                Toast.makeText(getContext(), "你该购物了", Toast.LENGTH_SHORT).show();
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void fetchCartData() {
        RestClient.builder()
                .url("cart/")
                .loader(getContext())
                .success(new ISuccess<ShopCartEntity>() {
                    @Override
                    public void onSuccess(ShopCartEntity response) {

                        //如果是加载更多
                        mAdapter.addData(new ShopCartDataConverter().setData(response).convert());
                        checkItemCount();
                        //如果是刷新
//                        mAdapter.setNewData(new ShopCartDataConverter().setData(response).convert());
                    }
                })
                .build()
                .get();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ShopCartAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setCartItemListener(itemTotalPrice -> {
            final double price = mAdapter.getTotalPrice();
            atvTotalprice.setText(String.valueOf(price));
            if (itemTotalPrice >= 0) {
                //TODO:something
            }
        });

//        checkItemCount();
    }

    private void createOrder() {
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        //TODO:需要app服务器约定的字段
        params.put("amount", 1.00);
        params.put("goods_id", "123");

        RestClient.builder()
                .url("")
                .params(params)
                .loader(getContext())
                .success(new ISuccess<String>() {
                    @Override
                    public void onSuccess(String response) {
                        //返回和app服务器约定的字段
                        final int orderId = JSON.parseObject(response).getInteger("result");

                        //订单创建成功后，拉起支付
                        FastPay.create(ShoppingCartDelegate.this)
                                .setPayResultCallback(ShoppingCartDelegate.this)
                                .setOrderId(orderId)
                                .startPayDialog();
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
