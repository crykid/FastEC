package com.blank.art.ec.sign;

import com.blank.art.app.AccountManager;
import com.blank.art.app.ISignListener;
import com.blank.art.ec.database.DatabaseManager;
import com.blank.art.ec.database.UserProfileEntry;

/**
 * Created by : blank
 * Created on : 2018/6/29 at 10:31
 * Description: 持久化个人信息
 */

public class SignHandler {

    public static void onSignUp(UserProfileEntry profileEntry, ISignListener iSignListener) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("dada");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");
//        final String phone = profileJson.getString("phone");
//
//        final UserProfileEntry profile = new UserProfileEntry(userId, name, avatar, gender, address, phone);

        DatabaseManager.getInstance().getDao().insert(profileEntry);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }

    public static void onSignIn(UserProfileEntry response, ISignListener iSignListener) {
        DatabaseManager.getInstance().getDao().insert(response);
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }
}
