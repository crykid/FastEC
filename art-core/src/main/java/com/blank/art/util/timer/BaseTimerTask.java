package com.blank.art.util.timer;

import java.util.TimerTask;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 10:59
 * Description:
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mItimerListener = null;

    public BaseTimerTask(ITimerListener itimerListener) {
        this.mItimerListener = itimerListener;
    }

    @Override
    public void run() {
        if (mItimerListener != null) {
            mItimerListener.onTimer();
        }
    }
}
