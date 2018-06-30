package com.blank.art.ec.database;

import com.alibaba.fastjson.annotation.JSONField;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 18:28
 * Description: 用户个人信息 实体类
 */
@Entity(nameInDb = "user_profile")
public class UserProfileEntry {

    /**
     * name='卢先生', gender='female', birthday='1994-03-07', email='1@1.com', mobile='15001994037'
     */
    @Id
    @JSONField(name = "id")
    public long userId;


    /**
     * name : 卢先生
     * gender : female
     * birthday : 1994-03-07
     * email : 1@1.com
     * mobile : 15001994037
     */
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "gender")
    public String gender;
    @JSONField(name = "birthday")
    public String birthday;
    @JSONField(name = "email")
    public String email;
    @JSONField(name = "mobile")
    public String mobile;

    ////////////////////////////////////以下是greenDao自动生成得代码，一般实体类不需要/////////////////////////////////////////////////////


    @Generated(hash = 1032865395)
    public UserProfileEntry(long userId, String name, String gender, String birthday, String email, String mobile) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.mobile = mobile;
    }


    @Generated(hash = 1149419086)
    public UserProfileEntry() {
    }


    @Override
    public String toString() {
        return "UserProfileEntry{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }


    public long getUserId() {
        return this.userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getBirthday() {
        return this.birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return this.mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
