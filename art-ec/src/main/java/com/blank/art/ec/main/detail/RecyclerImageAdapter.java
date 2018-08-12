package com.blank.art.ec.main.detail;

import android.support.v7.widget.AppCompatImageView;

import com.blank.art.ec.R;
import com.blank.art.ui.recycler.ItemType;
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
 * Created on: 2018/8/12.20:04
 * Description:商品详情-商品具体信息区域图片适配器
 */


public class RecyclerImageAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();

    protected RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        final int type = holder.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView imageView = holder.getView(R.id.image_rv_item);
                final String url = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(url)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
