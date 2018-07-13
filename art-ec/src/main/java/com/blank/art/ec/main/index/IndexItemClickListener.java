package com.blank.art.ec.main.index;

import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.main.detail.GoodsDetailDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by:blank
 * Created on: 2018/7/14.0:56
 * Description:列表点击事件监听实现类
 */
public class IndexItemClickListener extends SimpleClickListener {

    private final ArtDelegate DELEGATE;

    private IndexItemClickListener(ArtDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(ArtDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.start(detailDelegate);
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
