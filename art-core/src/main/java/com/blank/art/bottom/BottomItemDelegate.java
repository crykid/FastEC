package com.blank.art.bottom;

import android.widget.Toast;

import com.blank.art.delegates.ArtDelegate;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:主页面承载类fragment基类
 */
public abstract class BottomItemDelegate extends ArtDelegate {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
//        View rootView = getView();

//        if (rootView != null) {
//            //keylistener生效
//            rootView.setFocusableInTouchMode(true);
//            rootView.requestFocus();
//        }
    }


    /**
     * 也可以实现View.OnKeyListener实现以下功能
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {

        if (System.currentTimeMillis() - EXIT_TIME > mExitTime) {
            Toast.makeText(getContext(), "再次点击退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            _mActivity.finish();
            if (mExitTime != 0) {
                mExitTime = 0;
            }
        }
        return true;
    }
}
