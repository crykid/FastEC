package com.blank.art.ec.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by:blank
 * Created on: 2018/7/14.1:04
 * Description:商品详情
 */
public class GoodsDetailDelegate extends ArtDelegate {

    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object getLyout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //打开动画，添加一个水平载入的动画
        return new DefaultHorizontalAnimator();
    }
}
