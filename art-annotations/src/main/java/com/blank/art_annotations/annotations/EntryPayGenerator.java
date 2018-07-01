package com.blank.art_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:编译期间使用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryPayGenerator {
    String packageName();

    Class<?> payEntryTemplete();
}
