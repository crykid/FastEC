package com.blank.art.ec.main.sort.list;

import com.blank.art.ec.entry.CategoriesEntiry;
import com.blank.art.ec.entry.SubCategoryEntiry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 13:59
 * Description:商品种类-子类别数据转换器
 */

public class SubCategoryListDataConveter {


    public ArrayList<SubCategoryEntiry> convert(CategoriesEntiry catDetail) {
        //这是一个对象，对象包含subcat
        ArrayList<SubCategoryEntiry> dataList = new ArrayList<>();
        //取出对象的subcat
        List<CategoriesEntiry.SubCatBeanX> subCats = catDetail.subCat;
        final int size = subCats.size();
        //取出subcat的每一个对象
        for (int i = 0; i < size; i++) {
            CategoriesEntiry.SubCatBeanX subCat = subCats.get(i);

            SubCategoryEntiry subCategoryEntiry = new SubCategoryEntiry(true, subCat.name);

            subCategoryEntiry.mId = subCat.id;
            subCategoryEntiry.mIsMore = true;

            dataList.add(subCategoryEntiry);

            //取出第二层sub的subcatlist
            List<CategoriesEntiry.SubCatBeanX.SubCatBean> subCatBeans = subCat.subCat;
            int subSize = subCatBeans.size();
            //取出第三层的sub对象
            for (int j = 0; j < subSize; j++) {
                CategoriesEntiry.SubCatBeanX.SubCatBean bean = subCatBeans.get(j);
                SubCategoryEntiry entiry = new SubCategoryEntiry(bean);

                dataList.add(entiry);
            }

        }


        return dataList;
    }
}
