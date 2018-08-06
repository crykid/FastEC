package com.blank.art.ec.main.user.list;

import android.widget.CompoundButton;

import com.blank.art.delegates.ArtDelegate;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by : blank
 * Created on : 2018/8/3 at 13:47
 * Description:
 */

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private ArtDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mListener = null;


    public String getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public String getValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public ArtDelegate getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getListener() {
        return mListener;
    }

    public ListBean(int itemType, String imageUrl, String text, String value, int id, ArtDelegate delegate, CompoundButton.OnCheckedChangeListener listener) {
        this.mItemType = itemType;
        this.mImageUrl = imageUrl;
        this.mText = text;
        this.mValue = value;
        this.mId = id;
        this.mDelegate = delegate;
        this.mListener = listener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener listener = null;
        private ArtDelegate delegate = null;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;

        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;

        }

        public Builder setText(String text) {
            this.text = text;
            return this;

        }

        public Builder setValue(String value) {
            this.value = value;
            return this;

        }

        public Builder setListener(CompoundButton.OnCheckedChangeListener listener) {
            this.listener = listener;
            return this;

        }

        public Builder setDelegate(ArtDelegate delegate) {
            this.delegate = delegate;
            return this;

        }

        public ListBean build() {
            return new ListBean(itemType, imageUrl, text, value, id, delegate, listener);
        }
    }
}
