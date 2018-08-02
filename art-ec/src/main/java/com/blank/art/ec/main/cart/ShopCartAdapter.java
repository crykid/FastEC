package com.blank.art.ec.main.cart;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.blank.art.app.Art;
import com.blank.art.ec.R;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.blank.art.ui.recycler.MultipleRecyclerAdapter;
import com.blank.art.ui.recycler.MultipleViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collection;
import java.util.List;

/**
 * Created by:blank
 * Created on: 2018/7/31.23:19
 * Description:
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    private ICartItemListener mCartItemListener;

    public void setCartItemListener(ICartItemListener cartItemListener) {
        this.mCartItemListener = cartItemListener;
    }

    //购物车总价值
    private double mTotalPrice = 0.00;

    private static final RequestOptions options = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shopping_cart);

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:

                final boolean selected_current = entity.getField(ShopCartItemFields.SELECTED);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final String id = entity.getField(MultipleFields.ID) + "";

                if (selected_current) {

                    holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.theme));
                } else {
                    holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.wechat_black));

                }

                holder.setText(R.id.atv_item_cart_goods_title, entity.getField(ShopCartItemFields.TITLE));
                holder.setText(R.id.atv_item_cart_goods_desc, entity.getField(ShopCartItemFields.DESC));
                holder.setText(R.id.atv_item_cart_goods_price, String.valueOf(Double.valueOf(price).doubleValue()));
                holder.setText(R.id.atv_item_cart_goods_count, String.valueOf(count));

                Glide
                        .with(mContext)
                        .setDefaultRequestOptions(options)
                        .load(entity.getField(MultipleFields.IMAGE_URL))
                        .into((ImageView) holder.getView(R.id.aiv_item_cart_goods));

                holder.setOnClickListener(R.id.itv_item_cart_select, v -> {
                    final boolean selected = entity.getField(ShopCartItemFields.SELECTED);
                    final int localCount = entity.getField(ShopCartItemFields.COUNT);
                    if (selected) {

                        holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.wechat_black));
                        mTotalPrice -= price * localCount;
                        if (mCartItemListener != null) {
                            mCartItemListener.onItemClick(-1.00);
                        }

                    } else {
                        holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.theme));

                        mTotalPrice += price * localCount;
                        if (mCartItemListener != null) {
                            mCartItemListener.onItemClick(-1.00);
                        }

                    }
                    entity.setField(ShopCartItemFields.SELECTED, !selected);
                });

                holder.setOnClickListener(R.id.itv_item_cart_minus, v -> {
                    int localCount = entity.getField(ShopCartItemFields.COUNT);
                    double localPrice = entity.getField(ShopCartItemFields.PRICE);
                    boolean localSelected = entity.getField(ShopCartItemFields.SELECTED);

                    if (localCount >= 1) {
                        localCount -= 1;
                        //TODO:这里应该执行网络请求，执行成功后再执行以下代码
                        entity.setField(ShopCartItemFields.COUNT, localCount);
                        holder.setText(R.id.atv_item_cart_goods_count, String.valueOf(localCount));

                        if (mCartItemListener != null) {
                            if (localSelected) {
                                mTotalPrice -= localPrice;
                            }
                            final double itemTotal = localCount * localPrice;
                            mCartItemListener.onItemClick(itemTotal);
                        }

//                        RestClient.builder()
//                                .url("")
//                                .params("count", currentGoodsCurrent)
//                                .params("id", entity.getField(MultipleFields.ID))
//                                .success(new ISuccess() {
//                                    @Override
//                                    public void onSuccess(Object response) {
//                                        int count = Integer.valueOf(((AppCompatTextView) holder.getView(R.id.atv_item_cart_goods_count)).getText().toString());
//                                        count--;
//                                        entity.setField(ShopCartItemFields.COUNT, count);
//                                    }
//                                })
//                                .build()
//                                .post();
                    }
                });
                holder.setOnClickListener(R.id.itv_item_cart_plus, v -> {

                    int localCount = entity.getField(ShopCartItemFields.COUNT);
                    double localPrice = entity.getField(ShopCartItemFields.PRICE);
                    boolean localSelected = entity.getField(ShopCartItemFields.SELECTED);


                    localCount += 1;
                    //TODO:这里应该执行网络请求，执行成功后再执行以下代码
                    entity.setField(ShopCartItemFields.COUNT, localCount);
                    holder.setText(R.id.atv_item_cart_goods_count, String.valueOf(localCount));

                    if (mCartItemListener != null) {
                        if (localSelected) {
                            mTotalPrice += localPrice;
                        }
                        final double itemTotal = localCount * localPrice;
                        mCartItemListener.onItemClick(itemTotal);
                    }
                });

                break;
            default:
                break;
        }
    }

    @Override
    public void addData(@NonNull Collection<? extends MultipleItemEntity> newData) {
        super.addData(newData);
        initTotalPrice();

    }

    public void setSelectedAll(boolean selectedAll) {
        for (MultipleItemEntity entity : mData) {
            entity.setField(ShopCartItemFields.SELECTED, selectedAll);
        }
        initTotalPrice();
        if (mCartItemListener != null) {
            mCartItemListener.onItemClick(-1.00);
        }
    }

    private void initTotalPrice() {

        if (mData != null)
            for (MultipleItemEntity entity : mData) {
                if (entity.getField(ShopCartItemFields.SELECTED)) {
                    final double price = entity.getField(ShopCartItemFields.PRICE);
                    final int count = entity.getField(ShopCartItemFields.COUNT);
                    mTotalPrice += price * count;
                }
            }
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }
}
