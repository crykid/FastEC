package com.blank.art.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by : blank
 * Created on : 2018/6/28 at 18:34
 * Description: 数据库
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileEntryDao mDao = null;
    private final String DATABASE_NAME = "fast_ec.db";

    private DatabaseManager() {
    }


    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, DATABASE_NAME);

        final Database db = helper.getWritableDb();

        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileEntryDao();
    }

    public final UserProfileEntryDao getDao() {
        return mDao;
    }
}
