package com.blank.art.ec.main.cart;

import android.annotation.TargetApi;
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

    private ICartItemListener mCartItemListener;

    public void setCartItemListener(ICartItemListener cartItemListener) {
        this.mCartItemListener = cartItemListener;
    }

    //购物车总价值
    private double mTotalPrice = 0.00;

    private CartItemSelectListener mCartItemSelectListener = null;

    public void setmCartItemCountChangedListener(CartItemCountChangedListener listener) {
        this.mCartItemCountChangedListener = listener;
    }

    private CartItemCountChangedListener mCartItemCountChangedListener = null;

    public void setCartItemSelectListener(CartItemSelectListener cartItemSelectListener) {
        this.mCartItemSelectListener = cartItemSelectListener;
    }

    private static final RequestOptions options = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shopping_cart);
        for (MultipleItemEntity entity : data) {
            if (entity.getField(ShopCartItemFields.SELECTED)) {
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                mTotalPrice += price * count;
            }
        }
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

                    if (selected) {

                        holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.wechat_black));
                        //
                        if (mCartItemSelectListener != null) {
                            mCartItemSelectListener.onItemDisSelected(entity.getField(MultipleFields.ID), count, price);
                        }
                    } else {
                        holder.setTextColor(R.id.itv_item_cart_select, ContextCompat.getColor(Art.getApplicationContext(), R.color.theme));
                        if (mCartItemSelectListener != null) {
                            mCartItemSelectListener.onItemSelected(entity.getField(MultipleFields.ID), count, price);
                        }

                    }
                    entity.setField(ShopCartItemFields.SELECTED, !selected);
//                    notifyDataSetChanged();
                });

                holder.setOnClickListener(R.id.itv_item_cart_minus, v -> {
                    int localCount = entity.getField(ShopCartItemFields.COUNT);

                    if (localCount >= 1) {
                        localCount--;
                        //TODO:这里应该执行网络请求，执行成功后再执行以下代码
                        entity.setField(ShopCartItemFields.COUNT, localCount);
                        holder.setText(R.id.atv_item_cart_goods_count, String.valueOf(localCount));
                        if (mCartItemCountChangedListener != null) {
                            mCartItemCountChangedListener.onCountMinus(id, localCount, price);
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

                    int currentGoodsCurrent = entity.getField(ShopCartItemFields.COUNT);
                    currentGoodsCurrent++;
                    //TODO:这里应该执行网络请求，执行成功后再执行以下代码
                    entity.setField(ShopCartItemFields.COUNT, currentGoodsCurrent);
                    holder.setText(R.id.atv_item_cart_goods_count, String.valueOf(currentGoodsCurrent + 1));
                    if (mCartItemCountChangedListener != null) {
                        mCartItemCountChangedListener.onCountPlus(id, currentGoodsCurrent, price);
                    }
                });

                break;
            default:
                break;
        }
    }

    public void setSelectedAll(boolean selectedAll) {
        for (MultipleItemEntity entity : mData) {
            entity.setField(ShopCartItemFields.SELECTED, selectedAll);
        }
    }

    /**
     * 接口中无需传递变化的数量，因为每次调用时候，都是++，或--
     * <p>在计算总价格的时候，只需要在总价格 + / -price 就可以</p>
     * <p><strong>目前存在的问题：</strong>item数量的变化，需要请求接口，是在adapter中，还是所依赖的delegate中</p>
     */
    public interface CartItemCountChangedListener {
        /**
         * @param goodsId    商品id
         * @param totalCount 变化后的总数量
         * @param price      商品价格
         */
        void onCountPlus(String goodsId, int totalCount, double price);

        void onCountMinus(String goodsId, int totalCount, double price);
    }

    public interface CartItemSelectListener {
        /**
         * @param goodsId    被选中的商品id
         * @param totalCount 被选中的商品总数量
         * @param price      被选中的商品价格
         */
        void onItemSelected(String goodsId, int totalCount, double price);

        void onItemDisSelected(String goodsId, int totalCount, double price);
    }

}
