package com.blank.art.ec.main.sort.list;

import com.blank.art.ec.entity.CategoriesEntity;
import com.blank.art.ec.entity.SubCategoryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 13:59
 * Description:商品种类-子类别数据转换器
 */

public class SubCategoryListDataConveter {


    public ArrayList<SubCategoryEntity> convert(CategoriesEntity catDetail) {
        //这是一个对象，对象包含subcat
        ArrayList<SubCategoryEntity> dataList = new ArrayList<>();
        //取出对象的subcat
        List<CategoriesEntity.SubCatBeanX> subCats = catDetail.subCat;
        final int size = subCats.size();
        //取出subcat的每一个对象
        for (int i = 0; i < size; i++) {
            CategoriesEntity.SubCatBeanX subCat = subCats.get(i);

            SubCategoryEntity subCategoryEntity = new SubCategoryEntity(true, subCat.name);

            subCategoryEntity.mId = subCat.id;
            subCategoryEntity.mIsMore = true;

            dataList.add(subCategoryEntity);

            //取出第二层sub的subcatlist
            List<CategoriesEntity.SubCatBeanX.SubCatBean> subCatBeans = subCat.subCat;
            int subSize = subCatBeans.size();
            //取出第三层的sub对象
            for (int j = 0; j < subSize; j++) {
                CategoriesEntity.SubCatBeanX.SubCatBean bean = subCatBeans.get(j);
                SubCategoryEntity entiry = new SubCategoryEntity(bean);

                dataList.add(entiry);
            }

        }


        return dataList;
    }
}
