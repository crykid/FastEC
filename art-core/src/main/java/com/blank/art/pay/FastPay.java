package com.blank.art.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blank.art.R;
import com.blank.art.app.Art;
import com.blank.art.app.ConfigTypes;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.loader.Loader;
import com.blank.art.wechat.ArtWechat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;


/**
 * Created by : blank
 * Created on : 2018/8/3 at 00:38
 * Description: 快速支付工具类
 */

public class FastPay implements View.OnClickListener {
    //设置支付回调监听
    private IALiPayResult mALiPayResult = null;

    private Activity mActivity = null;

    private AlertDialog mDialog = null;

    private int mOrderId = -1;

    private FastPay(ArtDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(ArtDelegate delegate) {
        return new FastPay(delegate);
    }

    public void startPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            //透明
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.itv_dialog_pay_ali).setOnClickListener(this);
            window.findViewById(R.id.itv_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.abtn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultCallback(IALiPayResult callback) {
        this.mALiPayResult = callback;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderId = orderId;
        return this;
    }


    public final void aliPay(int orderId) {
        final String stringUrl = "你的服务端支付地址" + orderId;
        //获取签名字符串
        RestClient.builder()
                .url(stringUrl)
                .success(new ISuccess<String>() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = response;

                        //必须是异步的调用客户端支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mALiPayResult);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);

                    }
                })
                .build()
                .get();

    }

    public final void weChatPay(int orderId) {
        Loader.stopLoading();

        final String weChatPayUrl = "你的服务端微信预支付地址" + orderId;

        final IWXAPI iwxapi = ArtWechat.getInstance().getWXAPI();
        final String appId = Art.getConfiguration(ConfigTypes.WECHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPayUrl)
                .success(new ISuccess<String>() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result = JSON.parseObject(response).getJSONObject("result");

                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);

                    }
                })
                .build()
                .post();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.itv_dialog_pay_ali) {
            aliPay(mOrderId);
            if (mDialog != null) {
                mDialog.cancel();
            }
        } else if (id == R.id.itv_dialog_pay_wechat) {
            weChatPay(mOrderId);
            if (mDialog != null) {
                mDialog.cancel();
            }

        } else if (id == R.id.abtn_dialog_pay_cancel) {

        }
        if (mDialog != null) {
            mDialog.cancel();
        }
    }

}
