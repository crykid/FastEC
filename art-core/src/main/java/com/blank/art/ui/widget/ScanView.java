package com.blank.art.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by : blank
 * Created on : 2018/8/12 at 12:05
 * Description:二维码扫描自定义样式
 */

public class ScanView extends ZBarScannerView {


    public ScanView(Context context) {
        this(context, null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return new FinderView(context);
    }
}
