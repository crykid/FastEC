package com.blank.art.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blank.art.R;
import com.blank.art.delegates.ArtDelegate;

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.itv_dialog_pay_ali) {

        } else if (id == R.id.itv_dialog_pay_wechat) {

        } else if (id == R.id.abtn_dialog_pay_cancel) {

        }
        if (mDialog != null) {
            mDialog.cancel();
        }
    }

}
