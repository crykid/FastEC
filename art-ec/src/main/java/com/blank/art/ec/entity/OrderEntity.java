package com.blank.art.ec.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单实体类
 */
public class OrderEntity {
    /**
     * id : 201808031413
     * name : 秋裤
     * desc : 秋裤秋裤，衣中神器，穿上不冷，性感时尚
     * price : 1.00
     * address : lal阿斯顿发a
     * status : 1
     */

    @JSONField(name = "id")
    public long id;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "desc")
    public String desc;
    @JSONField(name = "price")
    public String price;
    @JSONField(name = "address")
    public String address;
    @JSONField(name = "goods_cover")
    public String goodsCover;
    @JSONField(name = "status")
    public int status;
    @JSONField(name = "order_time")
    public String orderTime;
}