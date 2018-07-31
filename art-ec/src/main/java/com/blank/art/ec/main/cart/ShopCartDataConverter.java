package com.blank.art.ec.main.cart;

import com.blank.art.ec.entry.ShopCartEntity;
import com.blank.art.ui.recycler.DataConverter;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:blank
 * Created on: 2018/7/31.22:43
 * Description:
 */
public class ShopCartDataConverter extends DataConverter<ShopCartEntity> {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> datalist = new ArrayList<>();
        ShopCartEntity entity = getData();
        List<ShopCartEntity.ResultsBean> realList = entity.results;

        int size = realList.size();

        for (ShopCartEntity.ResultsBean bean : realList) {
            final MultipleItemEntity entity1 = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, bean.id)
                    .setField(MultipleFields.IMAGE_URL, bean.goodsFrontImage)
                    .setField(ShopCartItemFields.TITLE, bean.name)
                    .setField(ShopCartItemFields.DESC, bean.goodsDesc)
                    .setField(ShopCartItemFields.SELECTED, false)
                    .setField(ShopCartItemFields.PRICE, bean.shopPrice)
                    .setField(ShopCartItemFields.COUNT, bean.count)
                    .build();
            datalist.add(entity1);

        }

        return datalist;
    }
}
