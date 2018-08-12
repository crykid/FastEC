package com.blank.art.ec.main.user.order;

import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 23:00
 * Description:
 */

public class OrderListClickListener extends SimpleClickListener {

    private final ArtDelegate DELEGATE;

    public OrderListClickListener(ArtDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DELEGATE.getSupportDelegate().start(new OrderCommentDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
