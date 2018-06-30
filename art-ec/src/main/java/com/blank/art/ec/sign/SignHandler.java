package com.blank.art.ec.sign;

import com.blank.art.ec.database.DatabaseManager;
import com.blank.art.ec.database.UserProfileEntry;

/**
 * Created by : blank
 * Created on : 2018/6/29 at 10:31
 * Description: 持久化个人信息
 */

public class  SignHandler {

    public static void onSignUp(UserProfileEntry profileEntry) {
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

    }
}
