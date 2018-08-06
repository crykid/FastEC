package com.blank.art.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blank.art.app.ISignListener;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.database.UserProfileEntry;
import com.blank.art.ec.entity.LoginEntry;
import com.blank.art.retrofit.RestClient;
import com.blank.art.retrofit.callback.IError;
import com.blank.art.retrofit.callback.IFailure;
import com.blank.art.retrofit.callback.IRequest;
import com.blank.art.retrofit.callback.ISuccess;
import com.blank.art.ui.loader.Loader;
import com.blank.art.util.storage.ArtPreference;
import com.blank.art.wechat.ArtWechat;
import com.blank.art.wechat.callbacks.IWeChatSigninCallback;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 17:19
 * Description:
 */

public class SignInDelegate extends ArtDelegate {
    private static final String TAG = "SignInDelegate";
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

    private ISignListener mISignListener = null;

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
            RestClient
                    .builder()
                    .url("login/")
                    .params("username", editUsername.getText().toString())
                    .params("password", editPass.getText().toString())
                    .request(new IRequest() {
                        @Override
                        public void onReqestStart() {
                            Loader.showLoading(getContext());
                        }

                        @Override
                        public void onRequestEnd() {
                            Loader.stopLoading();
                        }
                    })
                    .success(new ISuccess<LoginEntry>() {
                        @Override
                        public void onSuccess(LoginEntry response) {
                            ArtPreference.setToken(response.token);
                            Log.d(TAG, "onSuccess: " + response.token);
                            fetchUserProfile();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Log.d(TAG, "onFailure: ");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String message) {
                            Log.d(TAG, "onError: ");
                        }
                    })
                    .build()
                    .post();

        } else if (id == R.id.itv_sign_in_wechat) {
            Toast.makeText(getContext(), "微信登录", Toast.LENGTH_SHORT).show();
            ArtWechat.getInstance().onSigninSuccess(new IWeChatSigninCallback() {
                @Override
                public void onSignInSuccess(String userInfo) {

                }
            }).signIn();


        } else if (id == R.id.tv_sign_in_signup) {
            start(new SignUpDelegate());
        }

    }

    private void fetchUserProfile() {
        RestClient.builder()
                .url("users/1/")
                .request(new IRequest() {
                    @Override
                    public void onReqestStart() {

                    }

                    @Override
                    public void onRequestEnd() {
                        Loader.stopLoading();

                    }
                })
                .success(new ISuccess<UserProfileEntry>() {
                    @Override
                    public void onSuccess(UserProfileEntry response) {
                        SignHandler.onSignIn(response, mISignListener);
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
                .get();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


}
