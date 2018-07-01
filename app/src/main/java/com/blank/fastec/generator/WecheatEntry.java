package com.blank.fastec.generator;

import com.blank.art.templates.WXEntryTemplate;
import com.blank.art_annotations.annotations.EntryGenerator;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:
 */
@EntryGenerator(
        packageName = "com.blank.fastec",
        entryTemplete = WXEntryTemplate.class
)
public interface WecheatEntry {
}
