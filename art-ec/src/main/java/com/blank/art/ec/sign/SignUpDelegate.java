package com.blank.art.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blank.art.app.ISignListener;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.database.UserProfileEntry;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 16:04
 * Description:
 */

public class SignUpDelegate extends ArtDelegate {

    @BindView(R2.id.iv_sign_up_logo)
    AppCompatImageView ivLogo;
    @BindView(R2.id.edit_sign_up_username)
    TextInputEditText editSignUpUsername;
    @BindView(R2.id.edit_sign_up_pass)
    TextInputEditText editSignUpPass;
    @BindView(R2.id.edit_sign_up_repass)
    TextInputEditText editSignUpRepass;
    @BindView(R2.id.btn_sign_up_signup)
    Button btnSignup;
    @BindView(R2.id.tv_sign_up_signin)
    AppCompatTextView tvSignUpSignin;

    private ISignListener mISignListener = null;

    @Override
    public Object getLyout() {
        return R.layout.delegate_signuup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    private boolean checkForm() {

        final String name = editSignUpUsername.getText().toString();
        final String pass = editSignUpPass.getText().toString();
        final String rePass = editSignUpRepass.getText().toString();

        if (TextUtils.isEmpty(name)) {
            editSignUpUsername.setError("请输入用户名");
            return false;
        } else {
            editSignUpUsername.setError(null);
        }

        if (TextUtils.isEmpty(pass) || pass.length() < 6) {

            editSignUpPass.setError("请输入至少6位密码");
            return false;
        } else {
            editSignUpPass.setError(null);
        }

        if (TextUtils.isEmpty(rePass) || pass.length() < 6) {
            editSignUpRepass.setError("请输入至少6位密码");
            return false;
        } else {
            editSignUpRepass.setError(null);
        }

        if (!pass.equals(rePass)) {
            editSignUpRepass.setError("密码不匹配");
            return false;
        } else {
            editSignUpRepass.setError(null);
        }

        return true;
        //邮箱校验
//        if (TextUtils.isEmpty(pass)|| Patterns.EMAIL_ADDRESS.matcher(name).matches()){
//
//        }
    }


    @OnClick({R2.id.btn_sign_up_signup, R2.id.tv_sign_up_signin})
    public void onViewClicked(View view) {
        int id = view.getId();

        if (id == R.id.btn_sign_up_signup) {
            if (checkForm()) {
                RestClient.builder()
                        .url("users/")
                        .params("username", editSignUpUsername.getText().toString())
                        .params("code", "1122")
                        .params("mobile", editSignUpUsername.getText().toString())
                        .params("password", editSignUpPass.getText().toString())
                        .success(new ISuccess<UserProfileEntry>() {
                            @Override
                            public void onSuccess(UserProfileEntry response) {
                                SignHandler.onSignUp(response, mISignListener);
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure() {

                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String message) {

                            }
                        })
                        .build()
                        .post();
                Toast.makeText(getContext(), "注册ing", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.tv_sign_up_signin) {
            startWithPop(new SignInDelegate());

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }
}
