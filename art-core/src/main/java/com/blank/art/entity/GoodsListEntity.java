package com.blank.art.entity;

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
    public List<ResultsEntity> results;

    public static class ResultsEntity {
        @Override
        public String toString() {
            return "ResultsEntry{" +
                    "id=" + id +
                    ", category=" + category +
                    ", goodsSn='" + goodsSn + '\'' +
                    ", name='" + name + '\'' +
                    ", clickNum=" + clickNum +
                    ", soldNum=" + soldNum +
                    ", favNum=" + favNum +
                    ", goodsNum=" + goodsNum +
                    ", marketPrice=" + marketPrice +
                    ", shopPrice=" + shopPrice +
                    ", goodsBrief='" + goodsBrief + '\'' +
                    ", goodsDesc='" + goodsDesc + '\'' +
                    ", shipFree=" + shipFree +
                    ", goodsFrontImage='" + goodsFrontImage + '\'' +
                    ", isNew=" + isNew +
                    ", isHot=" + isHot +
                    ", addTime='" + addTime + '\'' +
                    ", images=" + images +
                    '}';
        }

        /**
         * id : 13
         * category : {"id":12,"sub_cat":[{"id":13,"sub_cat":[],"name":"松花蛋/咸鸭蛋","code":"xhd_xyd","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.775131","parent_category":12},{"id":14,"sub_cat":[],"name":"鸡蛋","code":"jd","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.775884","parent_category":12}],"name":"蛋制品","code":"dzp","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.774328","parent_category":1}
         * images : [{"image":"http://192.168.1.104:8000/media/goods/images/5_P_1448945270390.jpg"},{"image":"http://192.168.1.104:8000/media/goods/images/5_P_1448945270067.jpg"},{"image":"http://192.168.1.104:8000/media/goods/images/5_P_1448945270432.jpg"}]
         * goods_sn :
         * name : 澳洲进口安格斯牛切片上脑牛排1000g
         * click_num : 0
         * sold_num : 0
         * fav_num : 0
         * goods_num : 0
         * market_price : 144
         * shop_price : 120
         * goods_brief : 澳大利亚是国际公认的没有疯牛病和口蹄疫的国家。为了保持澳大利亚产品的高标准，澳大利亚牛肉业和各级政府共同努力简历了严格的标准和体系，以保证生产的整体化和产品的可追溯性
         * goods_desc : <p><img src="/media/goods/images/2_20170719161405_249.jpg" title="" alt="2.jpg"/></p><p><img src="/media/goods/images/2_20170719161414_628.jpg" title="" alt="2.jpg"/></p><p><img src="/media/goods/images/2_20170719161435_381.jpg" title="" alt="2.jpg"/></p>
         * ship_free : true
         * goods_front_image : http://192.168.1.104:8000/media/goods/images/5_P_1448945270390.jpg
         * is_new : false
         * is_hot : false
         * add_time : 2018-01-22T19:12:25.649300
         */

        @JSONField(name = "id")
        public int id;
        @JSONField(name = "category")
        public CategoryEntity category;
        @JSONField(name = "goods_sn")
        public String goodsSn;
        @JSONField(name = "name")
        public String name;
        @JSONField(name = "click_num")
        public int clickNum;
        @JSONField(name = "sold_num")
        public int soldNum;
        @JSONField(name = "fav_num")
        public int favNum;
        @JSONField(name = "goods_num")
        public int goodsNum;
        @JSONField(name = "market_price")
        public int marketPrice;
        @JSONField(name = "shop_price")
        public int shopPrice;
        @JSONField(name = "goods_brief")
        public String goodsBrief;
        @JSONField(name = "goods_desc")
        public String goodsDesc;
        @JSONField(name = "ship_free")
        public boolean shipFree;
        @JSONField(name = "goods_front_image")
        public String goodsFrontImage;
        @JSONField(name = "is_new")
        public boolean isNew;
        @JSONField(name = "is_hot")
        public boolean isHot;
        @JSONField(name = "add_time")
        public String addTime;
        @JSONField(name = "images")
        public List<ImagesEntity> images;

        public static class CategoryEntity {
            /**
             * id : 12
             * sub_cat : [{"id":13,"sub_cat":[],"name":"松花蛋/咸鸭蛋","code":"xhd_xyd","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.775131","parent_category":12},{"id":14,"sub_cat":[],"name":"鸡蛋","code":"jd","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.775884","parent_category":12}]
             * name : 蛋制品
             * code : dzp
             * desc :
             * category_type : 2
             * is_tab : false
             * add_time : 2018-01-22T18:48:50.774328
             * parent_category : 1
             */

            @JSONField(name = "id")
            public int id;
            @JSONField(name = "name")
            public String name;
            @JSONField(name = "code")
            public String code;
            @JSONField(name = "desc")
            public String desc;
            @JSONField(name = "category_type")
            public int categoryType;
            @JSONField(name = "is_tab")
            public boolean isTab;
            @JSONField(name = "add_time")
            public String addTime;
            @JSONField(name = "parent_category")
            public int parentCategory;
            @JSONField(name = "sub_cat")
            public List<SubCatEntity> subCat;

            public static class SubCatEntity {
                /**
                 * id : 13
                 * sub_cat : []
                 * name : 松花蛋/咸鸭蛋
                 * code : xhd_xyd
                 * desc :
                 * category_type : 3
                 * is_tab : false
                 * add_time : 2018-01-22T18:48:50.775131
                 * parent_category : 12
                 */

                @JSONField(name = "id")
                public int id;
                @JSONField(name = "name")
                public String name;
                @JSONField(name = "code")
                public String code;
                @JSONField(name = "desc")
                public String desc;
                @JSONField(name = "category_type")
                public int categoryType;
                @JSONField(name = "is_tab")
                public boolean isTab;
                @JSONField(name = "add_time")
                public String addTime;
                @JSONField(name = "parent_category")
                public int parentCategory;
                @JSONField(name = "sub_cat")
                public List<?> subCat;
            }
        }

        public static class ImagesEntity {
            /**
             * image : http://192.168.1.104:8000/media/goods/images/5_P_1448945270390.jpg
             */

            @JSONField(name = "image")
            public String image;
        }
    }

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
