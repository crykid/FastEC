package com.blank.art.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.entry.CategoriesEntiry;
import com.blank.art.ec.entry.SubCategoryEntiry;
import com.blank.art.ec.main.sort.list.SubCategoryListDataConveter;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/7/27 at 10:03
 * Description:根据左侧类型点击显示对应类型的内容
 */

public class SubCategoriesDelegate extends ArtDelegate {

    private static final String ARG_CONTENT_ID = "content_id";
    @BindView(R2.id.rlv_list_content)
    RecyclerView mRecyclerView;

    private int mContentId = -1;

    /**
     * 实际上，在分类接口中返回了全部分类，包含子类别，所以传参的时候大可以直接使用子类别，
     * 但是这个是一个demo，所以为了逻辑，仍然只传id，再去请求接口
     *
     * @param contentId
     * @return
     */
    public static SubCategoriesDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final SubCategoriesDelegate delegate = new SubCategoriesDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object getLyout() {
        return R.layout.delegate_list_content;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(manager);
        initData();


    }

    private void initData() {
        RestClient.builder()
                .url("categorys/" + mContentId + "/")
                .success(new ISuccess<CategoriesEntiry>() {
                    @Override
                    public void onSuccess(CategoriesEntiry response) {
                        final List<SubCategoryEntiry> data = new SubCategoryListDataConveter().convert(response);

                        final SubCategoryAdapter adapter = new SubCategoryAdapter(R.layout.item_subcategory_content, R.layout.item_subcategory_header, data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }


}
