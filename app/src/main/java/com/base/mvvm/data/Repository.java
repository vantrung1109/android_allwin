package com.base.mvvm.data;

import com.base.mvvm.data.local.prefs.PreferencesService;
import com.base.mvvm.data.local.sqlite.DbService;
import com.base.mvvm.data.remote.ApiService;


public interface Repository {

    /**
     * ################################## Preference section ##################################
     */
    String getToken();
    void setToken(String token);
    PreferencesService getSharedPreferences();


    /**
     * ################################## Sqlite section ##################################
     */
    DbService getSqliteService();



    /**
     *  ################################## Remote api ##################################
     */
    ApiService getApiService();


}
