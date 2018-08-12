package com.blank.art.wechat;

import android.app.Activity;

import com.blank.art.app.Art;
import com.blank.art.app.ConfigTypes;
import com.blank.art.wechat.callbacks.IWeChatSigninCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description: 存储微信appid和appSecret
 */
public class ArtWechat {

    public static final String APP_ID = (String) Art.getConfigurations().get(ConfigTypes.WECHAT_APP_ID);
    public static final String APP_SECRET = (String) Art.getConfigurations().get(ConfigTypes.WECHAT_APP_SECRET);

    private final IWXAPI WXAPI;

    private IWeChatSigninCallback mWeChatSigninCallback = null;


    private ArtWechat() {
        //初始化微信
        final Activity activity = (Activity) Art.getConfigurations().get(ConfigTypes.ACTIVITY);
        //传入appid用于校验，boolean值-是否校验（已经注册过了）
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    private static final class Holder {
        private static final ArtWechat INSTANCE = new ArtWechat();
    }

    public static ArtWechat getInstance() {
        return Holder.INSTANCE;
    }


    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public IWeChatSigninCallback getWeChatSigninCallback() {
        return mWeChatSigninCallback;
    }

    public ArtWechat onSigninSuccess(IWeChatSigninCallback weChatSigninCallback) {
        this.mWeChatSigninCallback = weChatSigninCallback;
        return this;
    }


    /**
     * 向微信客户端发送请求，表示我们要登录了
     */
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        //微信规定的字符串
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

}
