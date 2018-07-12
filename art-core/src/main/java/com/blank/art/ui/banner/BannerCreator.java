package com.blank.art.ui.banner;

import android.content.Context;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blank.art.R;

import java.util.ArrayList;

/**
 * Created by blank.
 * Created on 7/12/2018.
 * Description:
 */
public class BannerCreator {


    public static void setDefault(Context context, ConvenientBanner<String> convenientBanner, ArrayList<String> banners, OnItemClickListener clickListener) {
        convenientBanner
                .setPages(new HolderCreator(context), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .startTurning(3000)
                .setCanLoop(true);

    }
}
