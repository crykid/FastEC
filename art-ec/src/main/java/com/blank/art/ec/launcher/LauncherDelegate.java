package com.blank.art.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blank.art.app.AccountManager;
import com.blank.art.app.IUserChecker;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.sign.SignInDelegate;
import com.blank.art.ec.sign.SignUpDelegate;
import com.blank.art.ui.launcher.ILauncherListener;
import com.blank.art.ui.launcher.OnLuncherFinishTag;
import com.blank.art.ui.launcher.ScrollLauncherTag;
import com.blank.art.util.storage.ArtPreference;
import com.blank.art.util.timer.BaseTimerTask;
import com.blank.art.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 10:59
 * Description: 启动页面
 */

public class LauncherDelegate extends ArtDelegate implements ITimerListener {


    @BindView(value = R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer;


    private Timer mTimer = null;

    private int mCount = 3;

    private ILauncherListener mILauncherListener = null;

    @Override
    public Object getLyout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        initTimer();
    }

    private void checkIsShowScroll() {
        //如果是第一次启动
        if (!ArtPreference.getAppFlag(ScrollLauncherTag.FIRST_LAUNCHER.name())) {
            ArtPreference.setAppFlag(ScrollLauncherTag.FIRST_LAUNCHER.name(), true);
            startWithPop(new LauncherScrollDelegate());

        } else {
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLuncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLuncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    /**
     * 初始化计时器功能
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvLauncherTimer != null) {
                    tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                        tvLauncherTimer.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }

    }

    @OnClick(R2.id.tv_launcher_timer)
    public void onViewClicked() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        checkIsShowScroll();

    }
}
