package com.blank.art.ec.main.sort.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.main.sort.SortDelegate;
import com.blank.art.ec.main.sort.content.SubCategoriesDelegate;
import com.blank.art.ui.recycler.ItemType;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.blank.art.ui.recycler.MultipleRecyclerAdapter;
import com.blank.art.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 11:03
 * Description:分类页面列表的适配器
 */

public class CategoryListAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;

    private int mPresentPosition = 0;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CategoryListAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;

        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_sort_category);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:

                final String text = entity.getField(MultipleFields.TEXT);
                final boolean clicked = entity.getField(MultipleFields.TAG);

                final AppCompatTextView name = holder.getView(R.id.atv_item_category_name);
                final View leftLine = holder.getView(R.id.view_item_catetory_line_left);
                final View rightLine = holder.getView(R.id.view_item_catetory_line_right);

                final View itemView = holder.itemView;

                itemView.setOnClickListener(v -> {
                    //获取当前item的position
                    final int currentPosition = holder.getAdapterPosition();
                    if (mPresentPosition != currentPosition) {
                        getData().get(mPresentPosition).setField(MultipleFields.TAG, false);
//                        notifyDataSetChanged();

                        //更新选中的item
                        entity.setField(MultipleFields.TAG, true);
                        notifyDataSetChanged();
                        mPresentPosition = currentPosition;

                        final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                        showContent(contentId);
                    }
                });
                if (!clicked) {
                    leftLine.setVisibility(View.INVISIBLE);
                    rightLine.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.wechat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    leftLine.setVisibility(View.VISIBLE);
                    rightLine.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.theme));

                }
                holder.setText(R.id.atv_item_category_name, text);

                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final SubCategoriesDelegate delegate = SubCategoriesDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(SubCategoriesDelegate delegate) {
        final ArtDelegate contentDelegate = DELEGATE.findChildFragment(SubCategoriesDelegate.class);

        if (contentDelegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
