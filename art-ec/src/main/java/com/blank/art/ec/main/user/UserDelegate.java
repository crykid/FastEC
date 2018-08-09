package com.blank.art.ec.main.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.blank.art.bottom.BottomItemDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.main.user.address.AddressesDelegate;
import com.blank.art.ec.main.user.list.ListAdapter;
import com.blank.art.ec.main.user.list.ListBean;
import com.blank.art.ec.main.user.list.ListItemType;
import com.blank.art.ec.main.user.order.OrderListDelegate;
import com.blank.art.ec.main.user.profile.UserProfileDelegate;
import com.blank.art.ec.main.user.settings.SettingDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:个人中心
 */
public class UserDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_user_setting)
    RecyclerView rvUserSetting;
    @BindView(R2.id.rlayout_user_order_all)
    RelativeLayout rlAllOrder;
    @BindView(R2.id.civ_user_avatar)
    CircleImageView ivAvator;


    private Bundle mArgs = null;


    public final static String ORDER_TYPE = "ORDER_TYPE";

    @Override
    public Object getLyout() {
        return R.layout.delegate_user;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mArgs = new Bundle();
        //初始化用户页面列表item
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_NORMAL)
                .setId(1)
                .setText("地址管理")
                .setDelegate(new AddressesDelegate())
                .build();

        final ListBean settings = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_NORMAL)
                .setId(2)
                .setDelegate(new SettingDelegate())
                .setText("设置")
                .build();
        final ListBean profile = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TYPE_NORMAL)
                .setId(3)
                .setText("关于来尝鲜")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(settings);
//        data.add(profile);
        ListAdapter adapter = new ListAdapter(data);
        rvUserSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUserSetting.setAdapter(adapter);
        //添加自定义列表item点击事件处理
        rvUserSetting.addOnItemTouchListener(new UserClickListener(this));

    }

    @OnClick({R2.id.rlayout_user_order_all, R2.id.llayout_user_order_pay, R2.id.llayout_user_order_receive,
            R2.id.llayout_user_order_evaluate, R2.id.llayout_user_order_after_market,
            R2.id.civ_user_avatar})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rlayout_user_order_all) {
            mArgs.putString(ORDER_TYPE, "all");
            startOrderListByType();
        } else if (id == R.id.llayout_user_order_pay) {
            mArgs.putString(ORDER_TYPE, "pay");
            startOrderListByType();

        } else if (id == R.id.llayout_user_order_receive) {
            mArgs.putString(ORDER_TYPE, "receive");
            startOrderListByType();
        } else if (id == R.id.llayout_user_order_evaluate) {
            mArgs.putString(ORDER_TYPE, "evaluate");
            startOrderListByType();

        } else if (id == R.id.llayout_user_order_after_market) {
            mArgs.putString(ORDER_TYPE, "after_market");
            startOrderListByType();
        } else if (id == R.id.civ_user_avatar) {
            getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
        }

    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }
}
