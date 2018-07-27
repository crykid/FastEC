package com.blank.art.ui.recycler;

import java.util.ArrayList;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:数据转换基类
 */
public abstract class DataConverter<T> {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private T mGoodsListData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setData(T json) {
        this.mGoodsListData = json;
        return this;
    }

    protected T getData() {

        if (mGoodsListData == null) {
            throw new NullPointerException("DATA IS NULL !");
        }
        return mGoodsListData;
    }
}
