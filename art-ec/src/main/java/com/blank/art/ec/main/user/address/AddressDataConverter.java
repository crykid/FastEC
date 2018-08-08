package com.blank.art.ec.main.user.address;

import com.blank.art.ec.entity.AddressesEntity;
import com.blank.art.ui.recycler.DataConverter;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 00:31
 * Description:地址列表数据转换
 */

public class AddressDataConverter extends DataConverter<List<AddressesEntity.AddressEntity>> {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> data = new ArrayList<>();

        List<AddressesEntity.AddressEntity> addresses = getData();

        for (AddressesEntity.AddressEntity entity : addresses) {
            MultipleItemEntity mie = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, entity.id)
                    .setField(AddressFieldsType.ADDRESS, entity.address)
                    .setField(AddressFieldsType.DEFAULT, entity.defaultX)
                    .setField(AddressFieldsType.NAME, entity.name)
                    .setField(AddressFieldsType.PHONE, entity.phone)
                    .build();
            data.add(mie);
        }

        return data;
    }
}
