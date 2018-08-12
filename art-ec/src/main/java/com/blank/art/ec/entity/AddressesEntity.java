package com.blank.art.ec.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 00:38
 * Description:
 */

public class AddressesEntity implements Parcelable {

    public AddressesEntity() {
    }

    /**
     * page : 1
     * more : 1
     * count : 3
     * addresses : [{"id":1,"default":true,"name":"姓名1","phone":"12345","address":"地址1"},{"id":2,"default":false,"name":"姓名2","phone":"12345","address":"地址2"},{"id":3,"default":false,"name":"姓名3","phone":"12345","address":"地址3"}]
     */

    @JSONField(name = "page")
    public int page;
    @JSONField(name = "more")
    public int more;
    @JSONField(name = "count")
    public int count;
    @JSONField(name = "addresses")
    public List<AddressEntity> addresses;

    protected AddressesEntity(Parcel in) {
        page = in.readInt();
        more = in.readInt();
        count = in.readInt();
    }

    public static final Creator<AddressesEntity> CREATOR = new Creator<AddressesEntity>() {
        @Override
        public AddressesEntity createFromParcel(Parcel in) {
            return new AddressesEntity(in);
        }

        @Override
        public AddressesEntity[] newArray(int size) {
            return new AddressesEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(more);
        dest.writeInt(count);
    }

    public static class AddressEntity {
        /**
         * id : 1
         * default : true
         * name : 姓名1
         * phone : 12345
         * address : 地址1
         */

        @JSONField(name = "id")
        public int id;
        @JSONField(name = "default")
        public boolean defaultX;
        @JSONField(name = "name")
        public String name;
        @JSONField(name = "phone")
        public String phone;
        @JSONField(name = "address")
        public String address;
    }
}
