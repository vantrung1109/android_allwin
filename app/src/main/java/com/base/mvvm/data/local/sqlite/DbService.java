package com.base.mvvm.data.local.sqlite;

import androidx.lifecycle.LiveData;

import com.base.mvvm.data.model.db.UserEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface DbService {

    Observable<List<UserEntity>> getAllDbUser();

    LiveData<List<UserEntity>> loadAllToLiveData();

    Observable<Long> insertUser(UserEntity user);

    Observable<Boolean> deleteUser(UserEntity user);

    Observable<UserEntity> findById(Long id);

}
