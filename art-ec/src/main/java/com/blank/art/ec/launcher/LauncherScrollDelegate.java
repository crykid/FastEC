package com.blank.art.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ui.launcher.LauncherHolderCreater;
import com.blank.art.ui.launcher.ScrollLauncherTag;
import com.blank.art.util.storage.ArtPreference;

import java.util.ArrayList;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 13:46
 * Description: 启动页面轮播滚动页面
 */

public class LauncherScrollDelegate extends ArtDelegate {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();


    @Override
    public Object getLyout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void initBanner() {
        INTEGERS.add(R.drawable.launcher_00);
        INTEGERS.add(R.drawable.launcher_01);
        INTEGERS.add(R.drawable.launcher_02);

        mConvenientBanner
                .setPages(new LauncherHolderCreater(R.layout.delegate_launcher_scroll), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (position == INTEGERS.size() - 1) {
                            ArtPreference.setAppFlag(ScrollLauncherTag.FIRST_LAUNCHER.name(), true);
                            //检查用户是否已经登录
                        }
                    }
                })
                .setCanLoop(true);

    }
}
