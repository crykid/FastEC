package com.blank.art.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ec.R2;
import com.blank.art.ec.sign.SignUpDelegate;
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

    Unbinder unbinder;

    private Timer mTimer = null;

    private int mCount = 5;

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
            start(new LauncherScrollDelegate(), SINGLETASK);
            pop();
        } else {
            //检查用户是否登录
            startWithPop(new SignUpDelegate());
        }
    }


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


    @OnClick(R2.id.tv_launcher_timer)
    public void onViewClicked() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        checkIsShowScroll();

    }
}
