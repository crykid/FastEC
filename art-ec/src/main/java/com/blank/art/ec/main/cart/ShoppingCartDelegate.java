package com.blank.art.ec.main.cart;

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
public class ShoppingCartDelegate extends BottomItemDelegate {
    @Override
    public Object getLyout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
