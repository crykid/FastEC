package com.blank.art.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.blank.art.ec.R;
import com.blank.art.ui.recycler.RgbValue;

/**
 * Created by:blank
 * Created on: 2018/7/14.0:19
 * Description:CoordinatorLayout添加依赖,处理recyclerView和Toolbar的关系。通过反射获取该类，不用显式的调用
 */
@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 30;

    //定义变化的颜色
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);

    /**
     * 必须重写两个参数的构造函数，因为在使用的时候调用了这个构造函数
     *
     * @param context
     * @param attrs
     */
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rlv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    /**
     * 处理具体的逻辑
     *
     * @param coordinatorLayout
     * @param child             被依赖的对象，就是我们的recyclerView
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     * @param type
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //增加华东距离
        mDistanceY += dy;
        //toolbar高度
        final int targetHeight = child.getBottom();

        //当滑动时，并且距离小于toolbar高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY < targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mDistanceY > targetHeight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }

    }
}
