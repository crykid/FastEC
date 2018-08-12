package com.blank.art.ec.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 商品详情
 */

public class GoodsDetailEntity implements Parcelable {
    @JSONField(name = "tabs")
    public List<TabEntity> tabs;

    public GoodsDetailEntity() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(goodsSn);
        dest.writeString(name);
        dest.writeInt(clickNum);
        dest.writeInt(soldNum);
        dest.writeInt(favNum);
        dest.writeInt(goodsNum);
        dest.writeInt(marketPrice);
        dest.writeInt(shopPrice);
        dest.writeString(goodsBrief);
        dest.writeString(goodsDesc);
        dest.writeByte((byte) (shipFree ? 1 : 0));
        dest.writeString(goodsFrontImage);
        dest.writeByte((byte) (isNew ? 1 : 0));
        dest.writeByte((byte) (isHot ? 1 : 0));
        dest.writeString(addTime);
    }

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

    public static class TabEntity {
        /**
         * name : 商品详情
         * pictures : ["http://192.168.1.7:8000/media/goods/images/9_P_1448944791617.jpg","http://192.168.1.7:8000/media/goods/images/9_P_1448944791617.jpg"]
         */

        @JSONField(name = "name")
        public String nameX;
        @JSONField(name = "pictures")
        public List<String> pictures;
    }


    protected GoodsDetailEntity(Parcel in) {
        id = in.readInt();
        goodsSn = in.readString();
        name = in.readString();
        clickNum = in.readInt();
        soldNum = in.readInt();
        favNum = in.readInt();
        goodsNum = in.readInt();
        marketPrice = in.readInt();
        shopPrice = in.readInt();
        goodsBrief = in.readString();
        goodsDesc = in.readString();
        shipFree = in.readByte() != 0;
        goodsFrontImage = in.readString();
        isNew = in.readByte() != 0;
        isHot = in.readByte() != 0;
        addTime = in.readString();
    }

    public static final Creator<GoodsDetailEntity> CREATOR = new Creator<GoodsDetailEntity>() {
        @Override
        public GoodsDetailEntity createFromParcel(Parcel in) {
            return new GoodsDetailEntity(in);
        }

        @Override
        public GoodsDetailEntity[] newArray(int size) {
            return new GoodsDetailEntity[size];
        }
    };
}
