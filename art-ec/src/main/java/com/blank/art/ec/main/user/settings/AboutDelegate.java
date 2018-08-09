package com.blank.art.ec.main.user.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 22:50
 * Description: 关于
 */

public class AboutDelegate extends ArtDelegate {
    @Override
    public Object getLyout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
