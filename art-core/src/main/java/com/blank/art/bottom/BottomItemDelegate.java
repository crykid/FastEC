package com.blank.art.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.blank.art.delegates.ArtDelegate;

/**
 * Created by blank.
 * Created on 7/11/2018.
 * Description:
 */
public abstract class BottomItemDelegate extends ArtDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            //keylistener生效
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {

        //点击了返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
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
        return false;

    }
}
