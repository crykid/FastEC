package com.blank.art.ec.main.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.blank.art.ec.entity.GoodsDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/12 at 18:51
 * Description:不会保存状态的adapter
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final List<GoodsDetailEntity.TabEntity> TAB_TITLES = new ArrayList<>();
    private final List<List<String>> PICTURES = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, List<GoodsDetailEntity.TabEntity> data) {
        super(fm);
        //获取tabs的信息，注意，这里的tabs是一条信息
        final int size = data.size();
        for (int i = 0; i < size; i++) {
            GoodsDetailEntity.TabEntity tab = data.get(i);
            TAB_TITLES.add(tab);
            PICTURES.add(tab.pictures);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ImageDelegate.create(PICTURES.get(0));
        } else if (position == 1) {
            return ImageDelegate.create(PICTURES.get(1));
        }

        return null;
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position).nameX;
    }
}
