package com.base.mvvm.constant;

public class Constants {
    public static final String DB_NAME = "mvvm.db";
    public static final String PREF_NAME = "mvvm.prefs";

    public static final String VALUE_BEARER_TOKEN_DEFAULT="NULL";

    //Local Action manager
    public static final String ACTION_EXPIRED_TOKEN ="ACTION_EXPIRED_TOKEN";

    public static final String TENANT_ID = "TENANT_ID";
    public static final String TENANT_URL = "TENANT_URL";

    //Local Action manager
    public static final String INSTAGRAM_LOGIN_URL = "https://www.instagram.com/accounts/login/";
    public static final String INSTAGRAM_URL = "https://www.instagram.com/";
    public static final String FILE_TYPE_AVATAR = "AVATAR";
    public static final String TOKEN = "KEY_BEARER_TOKEN";

    public static final String KEY_STORE_SETTING = "KEY_STORE_SETTING";
    public static final String KEY_USER_ADDRESSES = "userAddresses";
    public static final String KEY_USER_ADDRESS_POS = "userAddressPos";
    public static final String BLANK_FRAGMENT = "blank fragment";
    public static final String ACCOUNT_FRAGMENT = "account fragment";

    public static final String ONLINE_PAYPAL = "ONLINE_PAYPAL";
    public static final String OFFLINE_CARD = "OFFLINE_CARD";
    public static final String OFFLINE_CASH = "OFFLINE_CASH";
    public static final String FAVOURITE_FRAGMENT = "favourite fragment";
    public static final String ACTIVITY_FRAGMENT = "activity fragment";
    public static final String HOME_FRAGMENT = "home fragment";
    public static final String APP_DRIVER = "CUSTOMER_APP";
    public static final int BOOKING_STATE_CANCEL = -100;
    public static final int BOOKING_STATE_BOOKING = 0;
    public static final int BOOKING_STATE_DRIVER_ACCEPT = 100 ;
    public static final int BOOKING_STATE_PICKUP_SUCCESS = 200 ;
    public static final int BOOKING_STATE_DONE = 300 ;
    private Constants(){

    }
}
