package com.blank.art.ec.main.user.order;

import android.support.v7.widget.AppCompatImageView;

import com.blank.art.ec.R;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/6 at 22:13
 * Description:
 */

public class OrderListAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, BaseViewHolder> {


    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrdersItemType.ITEM_ORDER_LIST, R.layout.item_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItemEntity item) {
        switch (helper.getItemViewType()) {
            case OrdersItemType.ITEM_ORDER_LIST:
                final AppCompatImageView cover = helper.getView(R.id.aiv_item_order_cover);
                Glide.with(mContext).load(item.getField(MultipleFields.IMAGE_URL)).apply(OPTIONS).into(cover);
                helper.setText(R.id.atv_item_order_title, item.getField(OrderFields.TITLE));
                helper.setText(R.id.atv_item_order_price, item.getField(OrderFields.PRICE));
                helper.setText(R.id.atv_item_order_time, item.getField(OrderFields.TIME));

                break;
            default:
                break;
        }
    }


}
