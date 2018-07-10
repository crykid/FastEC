package com.blank.art.ec.main.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:
 */
public class UserDelegate extends BottomItemDelegate {
    @Override
    public Object getLyout() {
        return R.layout.delegate_user;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
