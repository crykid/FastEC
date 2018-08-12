package com.blank.art.wechat.templates;

import com.blank.art.wechat.ArtWechat;
import com.blank.art.wechat.BaseWXEntryActivity;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description:微信模板类.微信登录后会返回该模板类生成的activity
 */
public class WXEntryTemplate extends BaseWXEntryActivity {


    @Override
    protected void onSignInSuccess(String userInfo) {
        ArtWechat.getInstance().getWeChatSigninCallback().onSignInSuccess(userInfo);

    }

    /**
     * 返回之后不会自己消失
     */
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //悄悄地消失，不要任何动画
        overridePendingTransition(0, 0);
    }


}
