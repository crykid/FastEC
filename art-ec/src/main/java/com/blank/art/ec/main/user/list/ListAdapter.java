package com.blank.art.ec.main.user.list;

import android.widget.ImageView;

import com.blank.art.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/3 at 14:47
 * Description:
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

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
    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_TYPE_ARROW, R.layout.item_user_arrow);
        addItemType(ListItemType.ITEM_TYPE_AVATOR, R.layout.item_user_avator);
    }

    @Override
    protected void convert(BaseViewHolder holder, ListBean item) {
        switch (holder.getItemViewType()) {
            case ListItemType.ITEM_TYPE_ARROW:
                holder.setText(R.id.atv_item_user_arrow_text, item.getText());
                holder.setText(R.id.atv_item_user_arrow_value, item.getValue());
//                holder.itemView.setOnClickListener(v -> {
//                            if (item.getListener() != null){
//                                item.getListener().onCheckedChanged();
//                            }
//                        }
//                );
                break;
            case ListItemType.ITEM_TYPE_AVATOR:
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_arrow_avatar));
                break;
            default:
                break;
        }
    }
}
