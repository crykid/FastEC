package com.blank.art_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:用来传入包名，微信所需要得模板代码，绕过微信的限制，不在主程序中写微信相关代码
 */

/**
 * 用在类上面,源码上
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();

    Class<?> entryTemplete();
}
