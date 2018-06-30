package com.blank.art.ec.entry;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by : blank
 * Created on : 2018/6/29 at 16:42
 * Description:
 */

public class LoginEntry implements Serializable {


    /**
     * token : tokenheheh
     */

    @JSONField(name = "token")
    public String token;
}
