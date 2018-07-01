package com.blank.fastec.generator;

import com.blank.art.templates.WXPayEntryTemplate;
import com.blank.art_annotations.annotations.EntryPayGenerator;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:
 */
@EntryPayGenerator(packageName = "com.blank.fastec",
        payEntryTemplete = WXPayEntryTemplate.class)
public interface WecheatPayEntry {
}
