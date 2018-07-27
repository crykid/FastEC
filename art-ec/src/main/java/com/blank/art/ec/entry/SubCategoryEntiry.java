package com.blank.art.ec.entry;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 14:32
 * Description:
 */

public class SubCategoryEntiry extends SectionEntity<CategoriesEntiry.SubCatBeanX.SubCatBean> {

    public boolean mIsMore = false;
    public int mId = -1;


    public SubCategoryEntiry(CategoriesEntiry.SubCatBeanX.SubCatBean subCatBean) {
        super(subCatBean);
    }

    public SubCategoryEntiry(boolean isHeader, String header) {
        super(isHeader, header);
    }
}
