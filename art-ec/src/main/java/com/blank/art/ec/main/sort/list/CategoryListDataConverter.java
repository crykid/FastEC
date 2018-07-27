package com.blank.art.ec.main.sort.list;

import com.blank.art.ec.entry.CategoriesEntiry;
import com.blank.art.ui.recycler.DataConverter;
import com.blank.art.ui.recycler.ItemType;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 10:19
 * Description: 分类列表数据转换器
 */

public class CategoryListDataConverter extends DataConverter<List<CategoriesEntiry>> {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final List<CategoriesEntiry> categories = getData();

        final int size = categories.size();
        for (int i = 0; i < size; i++) {
            final CategoriesEntiry data = categories.get(i);
            final int id = data.id;
            final String name = data.name;

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG, true);
        }

        return dataList;
    }
}
