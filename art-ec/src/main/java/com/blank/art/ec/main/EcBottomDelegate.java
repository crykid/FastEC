package com.blank.art.ec.main;

import android.graphics.Color;

import com.blank.art.bottom.BaseBottomDegelate;
import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.bottom.BottomTabBean;
import com.blank.art.bottom.ItemBuilder;
import com.blank.art.ec.main.cart.ShoppingCartDelegate;
import com.blank.art.ec.main.index.IndexDelegate;
import com.blank.art.ec.main.sort.SortDelegate;
import com.blank.art.ec.main.user.UserDelegate;

import java.util.LinkedHashMap;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:app主页面
 */
public class EcBottomDelegate extends BaseBottomDegelate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        //货物，购物车，个人中心
        items.put(new BottomTabBean("{fa-archive}", "商品"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-list-ul}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShoppingCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "个人中心"), new UserDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#08A4CF");
    }
}
