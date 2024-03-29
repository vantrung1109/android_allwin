package com.base.mvvm.data.local.sqlite;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.base.mvvm.data.local.sqlite.dao.DbUserDao;
import com.base.mvvm.data.model.db.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DbUserDao getDbUserDao();
}
