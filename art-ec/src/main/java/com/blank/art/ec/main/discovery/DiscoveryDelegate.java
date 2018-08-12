package com.blank.art.ec.main.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.delegates.web.WebDelegateImpl;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/7/30 at 13:56
 * Description:
 */

public class DiscoveryDelegate extends BottomItemDelegate {
    @BindView(R2.id.cfl_discovery_webcontainer)
    ContentFrameLayout cflWebcontainer;

    @Override
    public Object getLayout() {
        return R.layout.delegate_discovery;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.cfl_discovery_webcontainer,delegate);
    }
}
