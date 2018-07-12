package com.blank.art.ui.recycler;

import com.blank.art.entity.GoodsListEntity;

import java.util.ArrayList;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:数据转换基类
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private GoodsListEntity mGoodsListData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setData(GoodsListEntity json) {
        this.mGoodsListData = json;
        return this;
    }

    protected GoodsListEntity getJsonData() {

        if (mGoodsListData == null) {
            throw new NullPointerException("DATA IS NULL !");
        }
        return mGoodsListData;
    }
}
