package com.blank.art.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:微信回调类-基类
 */
public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个必须写在onCreate中
        ArtWechat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }

    /**
     * 保证在各种手机上不会出现问题
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        ArtWechat.getInstance().getWXAPI().handleIntent(getIntent(), this);

    }


}
