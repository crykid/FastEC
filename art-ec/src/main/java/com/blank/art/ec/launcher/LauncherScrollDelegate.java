package com.blank.art.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blank.art.app.AccountManager;
import com.blank.art.app.IUserChecker;
import com.blank.art.delegates.ArtDelegate;
import com.blank.art.ec.R;
import com.blank.art.ui.launcher.ILauncherListener;
import com.blank.art.ui.launcher.LauncherHolderCreater;
import com.blank.art.ui.launcher.OnLuncherFinishTag;

import java.util.ArrayList;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 13:46
 * Description: 启动页面轮播滚动页面
 */

public class LauncherScrollDelegate extends ArtDelegate {
    private static final String TAG = "LauncherScrollDelegate";
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherListener mILauncherListener = null;


    @Override
    public Object getLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void initBanner() {
        Log.d(TAG, "initBanner: ");
        INTEGERS.add(R.drawable.ic_launcher);
        INTEGERS.add(R.drawable.ic_launcher);
        INTEGERS.add(R.drawable.ic_launcher);

        mConvenientBanner
                .setPages(new LauncherHolderCreater(R.layout.delegate_launcher_scroll), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (position == INTEGERS.size() - 1) {
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
//                            startWithPop(new SignUpDelegate());
                        }
                    }
                })
                .setCanLoop(true);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }

    }
}
