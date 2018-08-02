package com.blank.art.pay;

/**
 * Created by : blank
 * Created on : 2018/8/3 at 00:38
 * Description:
 */

public interface IALiPayResult {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
