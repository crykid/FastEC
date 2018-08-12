package com.blank.art.ec.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 14:32
 * Description:
 */

public class SubCategoryEntity extends SectionEntity<CategoriesEntity.SubCatBeanX.SubCatBean> {

    public boolean mIsMore = false;
    public int mId = -1;


    public SubCategoryEntity(CategoriesEntity.SubCatBeanX.SubCatBean subCatBean) {
        super(subCatBean);
    }

    public SubCategoryEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }
}
