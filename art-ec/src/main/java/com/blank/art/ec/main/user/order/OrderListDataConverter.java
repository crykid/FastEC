package com.blank.art.ec.main.user.order;

import com.blank.art.ec.entity.OrderEntity;
import com.blank.art.ui.recycler.DataConverter;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/3 at 16:10
 * Description:
 */

public class OrderListDataConverter extends DataConverter<List<OrderEntity>> {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final List<OrderEntity> orders = getData();
        final ArrayList<MultipleItemEntity> items = new ArrayList<>();
        for (OrderEntity entity : orders) {
            MultipleItemEntity me = MultipleItemEntity.builder()
                    .setItemType(OrdersItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, entity.id)
                    .setField(OrderFields.TITLE, entity.name)
                    .setField(OrderFields.PRICE, entity.price)
                    .setField(OrderFields.TIME, entity.orderTime)
                    .setField(MultipleFields.IMAGE_URL, entity.goodsCover)
                    .build();
            items.add(me);
        }
        return items;
    }
}
