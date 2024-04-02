package com.base.mvvm.data.local.sqlite;

import androidx.lifecycle.LiveData;

import com.base.mvvm.data.model.db.AccountEntity;
import com.base.mvvm.data.model.db.UserEntity;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class AppDbService implements DbService {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbService(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<UserEntity>> getAllDbUser() {
        return Observable.fromCallable(new Callable<List<UserEntity>>() {
            @Override
            public List<UserEntity> call() throws Exception {
                return mAppDatabase.getDbUserDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Long> insertUser(UserEntity user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mAppDatabase.getDbUserDao().insert(user);
            }
        });
    }

    @Override
    public Observable<Long> insertAccount(AccountEntity account) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mAppDatabase.getDbUserDao().insertAccount(account);
            }
        });
    }

    @Override
    public LiveData<List<UserEntity>> loadAllToLiveData() {
        return mAppDatabase.getDbUserDao().loadAllToLiveData();
    }


    @Override
    public Observable<Boolean> deleteUser(UserEntity user) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.getDbUserDao().delete(user);
                return true;
            }
        });
    }

    @Override
    public Observable<UserEntity> findById(Long id) {
        return Observable.fromCallable(new Callable<UserEntity>() {
            @Override
            public UserEntity call() throws Exception {
               return mAppDatabase.getDbUserDao().findById(id);
            }
        });
    }
}
