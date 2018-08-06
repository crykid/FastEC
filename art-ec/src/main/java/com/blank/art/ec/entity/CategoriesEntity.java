package com.blank.art.ec.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 10:40
 * Description:商品分类列表item entity
 */

public class CategoriesEntity implements Parcelable{

    public CategoriesEntity() {
    }

    /**
     * id : 1
     * sub_cat : [{"id":2,"sub_cat":[{"id":3,"name":"羊肉","code":"yr","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.765771","parent_category":2},{"id":4,"name":"禽类","code":"ql","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.767121","parent_category":2},{"id":5,"name":"猪肉","code":"zr","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.768257","parent_category":2},{"id":6,"name":"牛肉","code":"nr","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.769165","parent_category":2}],"name":"精品肉类","code":"jprl","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.764586","parent_category":1},{"id":7,"sub_cat":[{"id":8,"name":"参鲍","code":"cb","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.770910","parent_category":7},{"id":9,"name":"鱼","code":"yu","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.772003","parent_category":7},{"id":10,"name":"虾","code":"xia","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.772809","parent_category":7},{"id":11,"name":"蟹/贝","code":"xb","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.773573","parent_category":7}],"name":"海鲜水产","code":"hxsc","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.770044","parent_category":1},{"id":12,"sub_cat":[{"id":13,"name":"松花蛋/咸鸭蛋","code":"xhd_xyd","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.775131","parent_category":12},{"id":14,"name":"鸡蛋","code":"jd","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.775884","parent_category":12}],"name":"蛋制品","code":"dzp","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.774328","parent_category":1},{"id":15,"sub_cat":[{"id":16,"name":"生菜","code":"sc","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.777398","parent_category":15},{"id":17,"name":"菠菜","code":"bc","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.778152","parent_category":15},{"id":18,"name":"圆椒","code":"yj","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.778994","parent_category":15},{"id":19,"name":"西兰花","code":"xlh","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.779965","parent_category":15}],"name":"叶菜类","code":"ycl","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.776652","parent_category":1},{"id":20,"sub_cat":[],"name":"根茎类","code":"gjl","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.780881","parent_category":1},{"id":21,"sub_cat":[],"name":"茄果类","code":"qgl","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.781665","parent_category":1},{"id":22,"sub_cat":[],"name":"菌菇类","code":"jgl","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.782459","parent_category":1},{"id":23,"sub_cat":[],"name":"进口生鲜","code":"jksx","desc":"","category_type":2,"is_tab":false,"add_time":"2018-01-22T18:48:50.783338","parent_category":1}]
     * name : 生鲜食品
     * code : sxsp
     * desc : 生鲜食品
     * category_type : 1
     * is_tab : true
     * add_time : 2018-01-22T18:48:00
     * parent_category : null
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
    public Object parentCategory;
    @JSONField(name = "sub_cat")
    public List<SubCatBeanX> subCat;




    public static class SubCatBeanX {
        /**
         * id : 2
         * sub_cat : [{"id":3,"name":"羊肉","code":"yr","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.765771","parent_category":2},{"id":4,"name":"禽类","code":"ql","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.767121","parent_category":2},{"id":5,"name":"猪肉","code":"zr","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.768257","parent_category":2},{"id":6,"name":"牛肉","code":"nr","desc":"","category_type":3,"is_tab":false,"add_time":"2018-01-22T18:48:50.769165","parent_category":2}]
         * name : 精品肉类
         * code : jprl
         * desc :
         * category_type : 2
         * is_tab : false
         * add_time : 2018-01-22T18:48:50.764586
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
        public List<SubCatBean> subCat;

        public static class SubCatBean {
            /**
             * id : 3
             * name : 羊肉
             * code : yr
             * desc :
             * category_type : 3
             * is_tab : false
             * add_time : 2018-01-22T18:48:50.765771
             * parent_category : 2
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
        }
    }


    protected CategoriesEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        code = in.readString();
        desc = in.readString();
        categoryType = in.readInt();
        isTab = in.readByte() != 0;
        addTime = in.readString();
    }

    public static final Creator<CategoriesEntity> CREATOR = new Creator<CategoriesEntity>() {
        @Override
        public CategoriesEntity createFromParcel(Parcel in) {
            return new CategoriesEntity(in);
        }

        @Override
        public CategoriesEntity[] newArray(int size) {
            return new CategoriesEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(code);
        dest.writeString(desc);
        dest.writeInt(categoryType);
        dest.writeByte((byte) (isTab ? 1 : 0));
        dest.writeString(addTime);
    }

}
