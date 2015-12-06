package com.jash.dmgame.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jash.dmgame.dao.DaoMaster;
import com.jash.dmgame.dao.DaoSession;
import com.jash.dmgame.dao.TypeEntityDao;
import com.jash.dmgame.entities.TypeEntity;

/**
 * Created by jash
 * Date: 15-11-20
 * Time: 上午11:03
 */
public class DaoUtils {
    private static DaoSession session;

    public static void initialize(Context context){
        GameOpenHelper helper = new GameOpenHelper(context, "3dmgame.db", null);
        session = new DaoMaster(helper.getWritableDatabase()).newSession();

    }

    public static DaoSession getSession() {
        return session;
    }

    private static class GameOpenHelper extends DaoMaster.DevOpenHelper{

        public GameOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            super.onCreate(db);
            TypeEntityDao typeDao = new DaoMaster(db).newSession().getTypeEntityDao();
            typeDao.insertOrReplace(new TypeEntity(1L, "文章首页"));
            typeDao.insertOrReplace(new TypeEntity(2L, "游戏新闻"));
            typeDao.insertOrReplace(new TypeEntity(151L, "游戏杂谈"));
            typeDao.insertOrReplace(new TypeEntity(152L, "硬件信息"));
            typeDao.insertOrReplace(new TypeEntity(153L, "游戏前瞻"));
            typeDao.insertOrReplace(new TypeEntity(154L, "游戏评测"));
            typeDao.insertOrReplace(new TypeEntity(194L, "原创精品"));
            typeDao.insertOrReplace(new TypeEntity(197L, "游戏盘点"));
            typeDao.insertOrReplace(new TypeEntity(199L, "时事焦点"));
            typeDao.insertOrReplace(new TypeEntity(25L, "攻略中心"));
        }
    }
}
