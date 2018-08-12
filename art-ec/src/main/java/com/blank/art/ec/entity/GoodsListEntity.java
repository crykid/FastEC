package com.blank.art.ec.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:
 */
public class GoodsListEntity {
    @JSONField(name = "count")
    public int count;
    @JSONField(name = "next")
    public String next;
    @JSONField(name = "previous")
    public String previous;
    @JSONField(name = "results")
    public List<GoodsDetailEntity> results;


    @Override
    public String toString() {
        return "GoodsListEntry{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
