package com.blank.art.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.blank.art.R;
import com.blank.art.delegates.ArtDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 16:54
 * Description:
 */

public abstract class ProxyActivity extends SupportActivity {
    private static final String TAG = "ProxyActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Log.d(TAG, "onCreate: 执行了 ");
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "initContainer: 执行了");
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_containter);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_containter, getRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: 执行了");
        super.onDestroy();
        //不一定执行
        System.gc();
        System.runFinalization();
    }


    public abstract ArtDelegate getRootDelegate();
}
