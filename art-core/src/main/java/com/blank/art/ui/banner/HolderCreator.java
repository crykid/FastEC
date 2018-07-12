package com.blank.art.ui.banner;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.blank.art.R;

/**
 * Created by blank.
 * Created on 7/12/2018.
 * Description:
 */
public class HolderCreator implements CBViewHolderCreator {
    private final Context mContext;

    public HolderCreator(Context context) {
        this.mContext = context;

    }

    @Override
    public Holder createHolder(View itemView) {

        return new ImageHolder(mContext, itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_multiple_image;
    }
}
