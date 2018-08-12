package com.blank.art.ec.main.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entity.GoodsDetailEntity;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/8/12 at 18:33
 * Description:
 */

public class GoodsInfoDelegate extends ArtDelegate {

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;

    private static final String ARG_GOODS_DATA = "ARG_GOODS_DATA";
    private GoodsDetailEntity mGoodsDetail = null;

    @Override
    public Object getLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mGoodsDetail = args.getParcelable(ARG_GOODS_DATA);
    }

    public static GoodsInfoDelegate create(GoodsDetailEntity goodsInfo) {
        final Bundle args = new Bundle();
        args.putParcelable(ARG_GOODS_DATA, goodsInfo);
        final GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mGoodsInfoTitle.setText(mGoodsDetail.name);
        mGoodsInfoDesc.setText(mGoodsDetail.goodsBrief);
        mGoodsInfoPrice.setText(String.valueOf(mGoodsDetail.marketPrice));
    }
}
