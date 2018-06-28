package com.blank.art.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 17:19
 * Description:
 */

public class SignInDelegate extends ArtDelegate {
    @BindView(R2.id.iv_sign_in_avator)
    AppCompatImageView ivAvator;
    @BindView(R2.id.edit_sign_in_username)
    TextInputEditText editUsername;
    @BindView(R2.id.edit_sign_in_pass)
    TextInputEditText editPass;
    @BindView(R2.id.btn_sign_up_signin)
    Button btnSignin;
    @BindView(R2.id.itv_sign_in_wechat)
    IconTextView itvWechat;
    @BindView(R2.id.tv_sign_in_signup)
    AppCompatTextView tvSignInSignup;

    @Override
    public Object getLyout() {
        return R.layout.delegate_signin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @OnClick({R2.id.btn_sign_up_signin, R2.id.itv_sign_in_wechat, R2.id.tv_sign_in_signup})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_sign_up_signin) {
            Toast.makeText(getContext(), "登录", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.itv_sign_in_wechat) {
            Toast.makeText(getContext(), "微信登录", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.tv_sign_in_signup) {
            Toast.makeText(getContext(), "去注册", Toast.LENGTH_SHORT).show();
            start(new SignUpDelegate());
        }

    }


}