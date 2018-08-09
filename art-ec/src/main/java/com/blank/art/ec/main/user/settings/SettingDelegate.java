package com.blank.art.ec.main.user.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.main.user.address.AddressesDelegate;
import com.blank.art.ec.main.user.list.ListAdapter;
import com.blank.art.ec.main.user.list.ListBean;
import com.blank.art.ec.main.user.list.ListItemType;
import com.blank.art.util.callback.CallbackManager;
import com.blank.art.util.callback.CallbackType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by : blank
 * Created on : 2018/8/9 at 22:01
 * Description:
 */

public class SettingDelegate extends ArtDelegate {
    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Override
    public Object getLyout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_SWITCH)
                .setId(1)
                .setText("消息推送")
                .setListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        CallbackManager.getInstance().getCallback(CallbackType.OPEN_PUSH).executeCallback(null);
                        Toast.makeText(getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                        CallbackManager.getInstance().getCallback(CallbackType.STOP_PUSH).executeCallback(null);
                    }
                })
                .setDelegate(new AddressesDelegate())
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_NORMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();


        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        //添加自定义列表item点击事件处理
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));


    }


}
