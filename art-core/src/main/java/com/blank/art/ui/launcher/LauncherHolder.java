package com.blank.art.ui.launcher;


import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 13:53
 * Description:
 */

public class LauncherHolder extends Holder<Integer> {

    private AppCompatImageView mImageView = null;

    public LauncherHolder(View itemView) {
        super(itemView);
        mImageView = (AppCompatImageView) itemView;

    }

    @Override
    protected void initView(View itemView) {

    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
