package com.blank.art.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.blank.art.R;
import com.blank.art.R2;
import com.blank.art.delegates.ArtDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:导航页基类
 */
public abstract class BaseBottomDegelate extends ArtDelegate implements View.OnClickListener {

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    //存放所有的子fragment
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATE = new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;

    //默认fragment
    private int mIndexDelegate = 0;

    private int mClickedColor = Color.RED;

    /**
     * 初始化导航栏
     *
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object getLayout() {
        return R.layout.delegate_bottom;
    }

    /**
     * 设置默认启动的fragment，默认第一个
     *
     * @return
     */
    public abstract int setIndexDelegate();

    /**
     * 设置选中的tab颜色
     *
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);

        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean bean = item.getKey();
            final BottomItemDelegate delegate = item.getValue();

            TAB_BEANS.add(bean);
            ITEM_DELEGATE.add(delegate);
        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();

        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            final BottomTabBean bean = TAB_BEANS.get(i);

            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());

            //如果正好是当前fragment，设置被选中的颜色
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }
        final SupportFragment[] delegateArr = ITEM_DELEGATE.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArr);

    }

    /**
     * 当点击某个tab之后重置所有tab
     */
    private void resetColor() {
        final int count = mBottomBar.getChildCount();

        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            setItemColor(item, Color.GRAY);
        }
    }

    @Override
    public void onClick(View view) {
        final int tag = (int) view.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) view;

        setItemColor(item, mClickedColor);

        showHideFragment(ITEM_DELEGATE.get(tag), ITEM_DELEGATE.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }

    /**
     * 为选中的item设置颜色
     *
     * @param item
     * @param color
     */
    private void setItemColor(RelativeLayout item, int color) {

        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(color);

        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(color);

    }
}
