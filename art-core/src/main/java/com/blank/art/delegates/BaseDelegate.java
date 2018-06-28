package com.blank.art.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blank.art.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 16:57
 * Description: baseFragment
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnbinder = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;

        if (getLyout() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLyout(), container, false);
        } else if (getLyout() instanceof View) {
            rootView = (View) getLyout();
        }
        if (rootView != null) {

            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    /**
     * 设置view
     *
     * @return view的id或者view
     */
    public abstract Object getLyout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);
}
