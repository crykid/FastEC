package com.blank.art.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;


/**
 * Created by : blank
 * Created on : 2018/6/28 at 13:52
 * Description:
 */

public class LauncherHolderCreater implements CBViewHolderCreator {

    private int mLyoutId;

    public LauncherHolderCreater(int lyoutId) {
        this.mLyoutId = lyoutId;
    }

    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return mLyoutId;
    }
}
