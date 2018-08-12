package com.blank.art.ec.main.user.address;

import android.support.v7.widget.AppCompatTextView;

import com.blank.art.ec.R;
import com.blank.art.retrofit.RestClient;
import com.blank.art.ui.recycler.MultipleFields;
import com.blank.art.ui.recycler.MultipleItemEntity;
import com.blank.art.ui.recycler.MultipleRecyclerAdapter;
import com.blank.art.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 00:50
 * Description: 地址列表适配器
 */

public class AddressesAdapter extends MultipleRecyclerAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public AddressesAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        final String name = entity.getField(AddressFieldsType.NAME);
        final String phone = entity.getField(AddressFieldsType.PHONE);
        final String address = entity.getField(AddressFieldsType.ADDRESS);
        final boolean isDefault = entity.getField(AddressFieldsType.DEFAULT);
        final int id = entity.getField(MultipleFields.ID);

        final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
        final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
        final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
        final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);

        deleteTextView.setOnClickListener(v -> {
            RestClient.builder()
                    .url("addresses_remove/")
                    .params("id", id)
                    .success(response -> remove(holder.getLayoutPosition()))
                    .build()
                    .post();
        });

        nameText.setText(name);
        phoneText.setText(phone);
        addressText.setText(address);

    }
}
