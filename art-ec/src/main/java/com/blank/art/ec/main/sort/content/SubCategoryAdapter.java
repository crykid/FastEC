package com.blank.art.ec.main.sort.content;

import com.blank.art.ec.R;
import com.blank.art.ec.entry.SubCategoryEntiry;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 15:48
 * Description:
 */

public class SubCategoryAdapter extends BaseSectionQuickAdapter<SubCategoryEntiry, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SubCategoryAdapter(int layoutResId, int sectionHeadResId, List<SubCategoryEntiry> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SubCategoryEntiry item) {
//        SubCategoryEntiry sub = item.getField(MultipleFields.LIST);
        helper.setText(R.id.atv_item_subcat_header, item.header);
        helper.setVisible(R.id.atv_item_subcat_more, item.mIsMore);
        helper.addOnClickListener(R.id.atv_item_subcat_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoryEntiry item) {
//        SubCategoryEntiry sub = item.getField(MultipleFields.LIST);

        helper.setText(R.id.atv_item_subcat_content_name, item.t.name);

    }
}
