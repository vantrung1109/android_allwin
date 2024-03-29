package com.base.mvvm.data.local.sqlite.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.mvvm.data.model.db.UserEntity;

import java.util.List;

@Dao
public interface DbUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(UserEntity userEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> userEntities);

    @Query("SELECT * FROM db_users")
    List<UserEntity> loadAll();

    @Query("SELECT * FROM db_users")
    LiveData<List<UserEntity>> loadAllToLiveData();

    @Query("SELECT * FROM db_users WHERE id=:id")
    UserEntity findById(long id);

    @Delete
    void delete(UserEntity userEntity);

}
