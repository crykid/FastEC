package com.blank.art.ec.main.user.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.main.user.list.ListAdapter;
import com.blank.art.ec.main.user.list.ListBean;
import com.blank.art.ec.main.user.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by : blank
 * Created on : 2018/8/6 at 23:22
 * Description:用户信息
 */

public class UserProfileDelegate extends ArtDelegate {
    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView;

    @Override
    public Object getLyout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean avatar = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_AVATOR)
                .setId(3)
                .setText("头像")
                .setImageUrl("http://s1.sinaimg.cn/mw690/006LDoUHzy7auXElZGE40&690")
                .build();
        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_ARROW)
                .setId(1)
                .setText("姓名")
                .setValue("卢先生")
                .build();
        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_ARROW)
                .setId(1)
                .setText("性别")
                .setValue("男")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_ARROW)
                .setId(2)
                .setText("出生日期")
                .setValue("1994-01-26")
                .build();



        final List<ListBean> data = new ArrayList<>();
        data.add(avatar);
        data.add(name);
        data.add(gender);
        data.add(birth);


        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }


}
