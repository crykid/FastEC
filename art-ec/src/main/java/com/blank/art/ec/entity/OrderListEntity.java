package com.blank.art.ec.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/3 at 16:40
 * Description:
 */

public class OrderListEntity {

    /**
     * page : 1
     * more : 1
     * count : 3
     * orders : [{"id":201808031413,"name":"秋裤","desc":"秋裤秋裤，衣中神器，穿上不冷，性感时尚","price":"1.00","address":"lal阿斯顿发a","status":1},{"id":201808031414,"name":"短裤","desc":"好看的短裤","price":"3.00","address":"lal打法是否对a","status":2},{"id":201808031415,"name":"长裤裤","desc":"真漂亮真漂亮","price":"2.00","address":"la打la","status":3}]
     */

    @JSONField(name = "page")
    public int page;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "count")
    public int count;
    @JSONField(name = "orders")
    public List<OrderEntity> orders;


}
