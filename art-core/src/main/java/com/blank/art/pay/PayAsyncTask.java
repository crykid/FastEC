package com.blank.art.pay;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.blank.art.ui.loader.Loader;

/**
 * Created by : blank
 * Created on : 2018/8/3 at 09:35
 * Description:
 */

public class PayAsyncTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "PayAsyncTask";
    private final Activity ACTIVITY;
    private final IALiPayResult ALIPAYRESULT;


    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //用户取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //网络错误
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IALiPayResult alipayresult) {
        this.ACTIVITY = activity;
        this.ALIPAYRESULT = alipayresult;
    }


    @Override
    protected String doInBackground(String... params) {

        final String aliPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.pay(aliPaySign, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Loader.stopLoading();

        final PayResult payResult = new PayResult(result);
        //支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();

        Log.d(TAG, "onPostExecute: " + resultInfo);
        Log.d(TAG, "onPostExecute: " + resultStatus);

        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (ALIPAYRESULT != null) {
                    ALIPAYRESULT.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (ALIPAYRESULT != null) {
                    ALIPAYRESULT.onPayFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (ALIPAYRESULT != null) {
                    ALIPAYRESULT.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (ALIPAYRESULT != null) {
                    ALIPAYRESULT.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if (ALIPAYRESULT != null) {
                    ALIPAYRESULT.onPayConnectError();
                }
                break;
        }
    }
}
