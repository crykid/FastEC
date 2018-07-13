package com.blank.art.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by:blank
 * Created on: 2018/7/14.0:26
 * Description:用于存储rgb色值
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }

}
