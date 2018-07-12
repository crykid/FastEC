package com.blank.art.ec.main.index;

import android.text.TextUtils;

import com.blank.art.entity.GoodsListEntity;
import com.blank.art.ui.recycler.DataConverter;
import com.blank.art.ui.recycler.ItemType;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:将首页数据的集合拆分成对应的类型
 */
public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final List<GoodsListEntity.ResultsEntity> dataList = getJsonData().results;
        final int size = dataList.size();

        int type = 0;
//        goods_front_image，images，goods_brief，id
        for (int i = 0; i < size; i++) {
            GoodsListEntity.ResultsEntity resultEntity = dataList.get(i);
            String goodsFrontImage = resultEntity.goodsFrontImage;
            String goodsBrief = resultEntity.goodsBrief;
            String id = resultEntity.id + "";
            int spanSize = resultEntity.category.subCat.size();
            List<GoodsListEntity.ResultsEntity.ImagesEntity> imagesEntity = resultEntity.images;
            final List<GoodsListEntity.ResultsEntity.ImagesEntity> imagesEntityes = resultEntity.images;

            if (TextUtils.isEmpty(goodsFrontImage) && !TextUtils.isEmpty(goodsBrief)) {
                type = ItemType.TEXT;
            } else if (!TextUtils.isEmpty(goodsFrontImage) && TextUtils.isEmpty(goodsBrief)) {
                type = ItemType.IMAGE;
            } else if (imagesEntity != null) {
                type = ItemType.BANNER;
                imagesEntityes.addAll(imagesEntity);

            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, goodsBrief)
                    .setField(MultipleFields.IMAGE_URL, goodsFrontImage)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.BANNERS, imagesEntityes)
                    .build();
            ENTITIES.add(entity);
        }


        return ENTITIES;
    }
}
