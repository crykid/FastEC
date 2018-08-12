package com.blank.art.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.main.sort.content.SubCategoriesDelegate;
import com.blank.art.ec.main.sort.list.CategoryListDelegate;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 09:09
 * Description:商品分类页面
 */

public class SortDelegate extends BottomItemDelegate {
    @BindView(R2.id.cfl_sort_vertical_list_container)
    ContentFrameLayout cflSortVerticalListContainer;
    @BindView(R2.id.cfl_sort_content_container)
    ContentFrameLayout cflSortContentContainer;

    @Override
    public Object getLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final CategoryListDelegate listDelegate = new CategoryListDelegate();
        loadRootFragment(R.id.cfl_sort_vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        loadRootFragment(R.id.cfl_sort_content_container, SubCategoriesDelegate.newInstance(1));
    }


}
