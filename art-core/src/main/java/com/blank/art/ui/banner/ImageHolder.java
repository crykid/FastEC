package com.blank.art.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.blank.art.R;
import com.bumptech.glide.Glide;

/**
 * Created by blank.
 * Created on 7/12/2018.
 * Description:一般的banner
 */
public class ImageHolder extends Holder<String> {
    private final Context mContext;
    private AppCompatImageView mImageView;

    public ImageHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.aiv_item_image_single);

    }

    @Override
    public void updateUI(String data) {
        Glide.with(mContext).load(data).into(mImageView);

    }
}
