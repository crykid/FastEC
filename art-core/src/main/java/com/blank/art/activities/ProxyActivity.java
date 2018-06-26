package com.blank.art.activities;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.blank.art.R;
import com.blank.art.delegates.ArtDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 16:54
 * Description:
 */

public abstract class ProxyActivity extends SupportActivity {


    @Override
    final public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_containter);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_containter, getRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //不一定执行
        System.gc();
        System.runFinalization();
    }


    public abstract ArtDelegate getRootDelegate();
}
