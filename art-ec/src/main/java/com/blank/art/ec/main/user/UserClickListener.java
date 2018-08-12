package com.blank.art.ec.main.user;

import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.main.user.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 01:17
 * Description:用户各项设置列表点击事件
 */

public class UserClickListener extends SimpleClickListener {
    private final ArtDelegate DELEGATE;

    public UserClickListener(ArtDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);

        //注意，实际还是需要swith-case的，因为不能保证数据的正确性
//        switch (bean.getId()) {
//            case 1:
        if (bean.getDelegate() != null)
            DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            default:
//                break;
//        }
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
