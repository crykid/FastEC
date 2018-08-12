package com.blank.art.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.blank.art.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by : blank
 * Created on : 2018/8/10 at 22:46
 * Description:
 */

public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    private static final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECTED = "{fa-star}";

    private static final int STAR_TOTAL_COUNT = 5;

    private int mStarCount = 0;

    private static final ArrayList<IconTextView> STARS = new ArrayList<>();


    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    /**
     * 初始化图标
     */
    private void initStarIcon() {
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            //默认未选中
            star.setText(ICON_UN_SELECT);
            star.setTag(R.id.star_count, i);
            star.setTag(R.id.star_is_elected, false);
            star.setOnClickListener(this);
            STARS.add(star);
            addView(star);
        }
    }

    //    public int getStarCount() {
//        int count = 0;
//        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
//            final IconTextView star = STARS.get(i);
//            final boolean selected = (boolean) star.getTag(R.id.star_is_elected);
//            if (selected) {
//                count++;
//            }
//        }
//        return count;
//    }
    public int getStarCount() {
        return mStarCount;
    }

    //    private void selectStar(int count) {
//        final int total = STARS.size();
//        for (int i = 0; i < total; i++) {
//            final IconTextView star = STARS.get(i);
//            if (i <= count) {
//                star.setText(ICON_SELECTED);
//                star.setTag(R.id.star_is_elected, true);
//            } else {
//                star.setText(ICON_UN_SELECT);
//                star.setTag(R.id.star_is_elected, false);
//            }
//        }
//    }
//
    private void selectStar(int count) {
        for (int i = 0; i <= count; i++) {
            if (i <= count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_SELECTED);
                star.setTextColor(Color.RED);
                star.setTag(R.id.star_is_elected, true);
            }
        }
    }

    private void unSelectStar(int count) {
        for (int i = count; i < STAR_TOTAL_COUNT; i++) {
            if (i > count) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_elected, false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        final IconTextView star = (IconTextView) v;
        //获取第几个星星
        final int count = (int) star.getTag(R.id.star_count);
        mStarCount = count;
        //获取是否点击状态
        final boolean isSelected = (boolean) star.getTag(R.id.star_is_elected);
//        selectStar(count);
        if (!isSelected) {
            selectStar(count);
        } else {
            unSelectStar(count);
        }

    }
}
