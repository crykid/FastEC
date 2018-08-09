package com.blank.art.ec.main.user.settings;

import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.main.user.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 22:31
 * Description:
 */

public class SettingsClickListener extends SimpleClickListener {
    private final ArtDelegate DELEGATE;

    public SettingsClickListener(ArtDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        switch (bean.getId()) {
            case 1:
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getDelegate());
                break;
            case 3:
                break;
            default:
                break;
        }
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
