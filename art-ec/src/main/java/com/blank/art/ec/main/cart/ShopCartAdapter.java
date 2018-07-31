package com.blank.art.ec.main.cart;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
//    private final Context mContext;

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
                holder.setText(R.id.atv_item_cart_goods_title, entity.getField(ShopCartItemFields.TITLE));
                holder.setText(R.id.atv_item_cart_goods_desc, entity.getField(ShopCartItemFields.DESC));
                holder.setText(R.id.atv_item_cart_goods_price, entity.getField(ShopCartItemFields.PRICE) + "");
                holder.setText(R.id.atv_item_cart_goods_count, entity.getField(ShopCartItemFields.COUNT) + "");

                Glide
                        .with(mContext)
                        .setDefaultRequestOptions(options)
                        .load(entity.getField(MultipleFields.IMAGE_URL))
                        .into((ImageView) holder.getView(R.id.aiv_item_cart_goods));

                holder.setOnClickListener(R.id.itv_item_cart_select, v -> {
                    final boolean selected = entity.getField(ShopCartItemFields.SELECTED);

                    if (selected) {

                        holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.wechat_black));
                    } else {
                        holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.theme));

                    }
                    entity.setField(ShopCartItemFields.SELECTED, !selected);
//                    notifyDataSetChanged();
                });

                holder.setOnClickListener(R.id.itv_item_cart_minus, v -> {
                });
                holder.setOnClickListener(R.id.itv_item_cart_plus, v -> {
                });

                break;
            default:
                break;
        }
    }
}
