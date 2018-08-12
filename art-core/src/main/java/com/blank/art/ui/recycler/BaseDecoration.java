package com.blank.art.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by:blank
 * Created on: 2018/7/13.22:39
 * Description:分割线
 */
public class BaseDecoration extends DividerItemDecoration {
    /**
     * @param color 分割线颜色
     * @param size  分割线粗细
     */
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupIml(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
