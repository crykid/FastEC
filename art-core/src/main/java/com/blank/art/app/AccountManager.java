package com.blank.art.app;

import com.blank.art.util.storage.ArtPreference;

/**
 * Created by blank.
 * Created on 7/1/2018.
 * Description: 用户登录状态管理
 */
public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        ArtPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * @return 是否登录 true-已经登录
     */
    private static boolean isSignIn() {
        return ArtPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查是否已经登录，根据登录状态执行不同得回调方法
     *
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
