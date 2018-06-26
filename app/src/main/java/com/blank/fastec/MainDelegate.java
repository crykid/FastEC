package com.blank.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 18:08
 * Description:
 */

public class MainDelegate extends ArtDelegate {

    @Override
    public Object getLyout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
