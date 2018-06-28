package com.blank.art.ec.database;

import android.content.Context;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 18:34
 * Description:
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;
    private final String DATABASE_NAME = "fast_ec.db";

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, DATABASE_NAME);
    }
}
