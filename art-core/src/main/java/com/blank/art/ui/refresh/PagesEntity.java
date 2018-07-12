package com.blank.art.ui.refresh;

/**
 * Created by blank.
 * Created on 7/12/2018.
 * Description:
 */
public class PagesEntity {
    //当前页码
    private int mCourrentPage = 0;
    //总数据量
    private int mTotalPages = 0;
    //每一页显示的数量
    private int mPageSize = 0;
    //当前已经显示了几条数据
    private int mCurrentCount = 0;
    //加载延迟
    private int mDelayed = 0;

    public int getCourrentPage() {
        return mCourrentPage;
    }

    public PagesEntity setCourrentPage(int mCourrentPage) {
        this.mCourrentPage = mCourrentPage;
        return this;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public PagesEntity setTotalPages(int mTotalPages) {
        this.mTotalPages = mTotalPages;
        return this;

    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagesEntity setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;

    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagesEntity setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;

    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagesEntity setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;

    }
}
