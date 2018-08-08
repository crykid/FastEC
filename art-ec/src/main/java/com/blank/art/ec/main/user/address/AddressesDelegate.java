package com.blank.art.ec.main.user.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entity.AddressesEntity;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 00:46
 * Description:地址管理
 */

public class AddressesDelegate extends ArtDelegate  {

    @BindView(R2.id.icon_address_add)
    IconTextView iconAddressAdd;
    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView;
    AddressesAdapter mAdapter;

    @Override
    public Object getLyout() {
        return R.layout.delegate_addresses;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("addresses/")
                .loader(getContext())
                .success(new ISuccess<AddressesEntity>() {
                    @Override
                    public void onSuccess(AddressesEntity response) {
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mAdapter = new AddressesAdapter(new AddressDataConverter().setData(response.addresses).convert());
                        mRecyclerView.setAdapter(mAdapter);
                    }
                })
                .build()
                .get();
    }



}
