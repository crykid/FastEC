package com.blank.art.bottom;

import java.util.LinkedHashMap;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:
 */
public final class ItemBuilder {

    //有序的map
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {

        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {

        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
