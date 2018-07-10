package com.blank.fastec.generator;

import com.blank.art.wechat.templates.WXPayEntryTemplate;
import com.blank.art_annotations.annotations.PayEntryGenerator;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:
 */
@PayEntryGenerator(packageName = "com.blank.fastec",
        payEntryTemplete = WXPayEntryTemplate.class)
public interface WecheatPayEntry {
}
