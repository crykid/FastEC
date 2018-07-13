package com.blank.art.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by:blank
 * Created on: 2018/7/13.22:41
 * Description: 处理横向和垂直方向的属性
 */
public class DividerLookupIml implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookupIml(int color, int size) {
        this.COLOR = color;
        this.SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return create();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return create();
    }

    private Divider create() {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }
}
