package com.blank.art.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.blank.art.R;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by : blank
 * Created on : 2018/8/12 at 12:31
 * Description:二维码扫描自定义样式
 */

public class FinderView extends ViewFinderView {
    public FinderView(Context context) {
        this(context, null);
    }

    public FinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(ContextCompat.getColor(getContext(), R.color.theme));
    }
}
